package ru.zavialov.restApiProject.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.zavialov.restApiProject.dto.MeasurementDto;
import ru.zavialov.restApiProject.models.Measurement;
import ru.zavialov.restApiProject.models.Sensor;
import ru.zavialov.restApiProject.service.MeasurementService;
import ru.zavialov.restApiProject.service.SensorService;
import ru.zavialov.restApiProject.util.CreateException;
import ru.zavialov.restApiProject.util.ResponseError;

@RequestMapping("/measurements")
@RestController
public class MeasurementController {
	private final MeasurementService measurementService;
	private final SensorService sensorService;

	@Autowired
	public MeasurementController(MeasurementService measurementService, SensorService sensorService) {
		super();
		this.measurementService = measurementService;
		this.sensorService = sensorService;
	}

	@GetMapping("/all")
	public List<Measurement> findAll() {
		return measurementService.findAll();
	}
	
	@PostMapping("/add")
	public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid Measurement measurement, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			/*
			StringBuilder msg = new StringBuilder();
			List <FieldError> errors = bindingResult.getFieldErrors();
			
			for (FieldError error : errors) {
				msg.append(error.getField());
				msg.append(": ");
				msg.append(error.getDefaultMessage());
				msg.append(";");
			}
			throw new CreateException(msg.toString());
			*/
		}
		
		Sensor dbSensor = sensorService.findByName(measurement.getSensor().getName());
		
		
		measurement.getSensor().setId(dbSensor.getId());
		
		measurementService.createMeasurment(measurement);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	private MeasurementDto measurementToDto(Measurement measurement) {
		ModelMapper mapper = new ModelMapper();
		MeasurementDto dto = mapper.map(measurement, MeasurementDto.class);
		return dto;
	}
	
	private Measurement dtoToMeasurement(MeasurementDto dto) {
		ModelMapper mapper = new ModelMapper();
		Measurement measurement = mapper.map(dto, Measurement.class);
		return measurement;
	}
	
	@ExceptionHandler
	private ResponseEntity<ResponseError> handleException(CreateException exception) {
		ResponseError error = new ResponseError(exception.getMessage());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
	
	
	

}
