package ru.zavialov.restApiProject.controllers;

import java.util.List;

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
import ru.zavialov.restApiProject.dto.SensorDto;
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
	public List<MeasurementDto> findAll() {
		
		return measurementService.findAll().stream().map(m -> measurementToDto(m))
				.toList();
	}
	
	@PostMapping("/add")
	public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDto measurementDto, BindingResult bindingResult) {
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
		
		measurementService.createMeasurment(dtoToMeasurement(measurementDto));
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	private MeasurementDto measurementToDto(Measurement measurement) {
		ModelMapper mapper = new ModelMapper();
		MeasurementDto measurementDto = mapper.map(measurement, MeasurementDto.class);
		
		SensorDto sensorDto = new SensorDto();
		sensorDto.setName(measurement.getSensor().getName());
				measurementDto.setSensorDto(sensorDto);
				
		System.out.println(sensorDto.getName());
		
		return measurementDto;
	}
	
	private Measurement dtoToMeasurement(MeasurementDto measurementDto) {
		ModelMapper mapper = new ModelMapper();
		Measurement measurement = mapper.map(measurementDto, Measurement.class);
		System.out.println(measurement);
		
		Sensor sensor = sensorService.findByName(measurementDto.getSensorDto().getName());
		measurement.setSensor(sensor);
		System.out.println(measurement);
		measurement.setId(sensor.getId());
		System.out.println(measurement);
		return measurement;
	}
	
	@ExceptionHandler
	private ResponseEntity<ResponseError> handleException(CreateException exception) {
		ResponseError error = new ResponseError(exception.getMessage());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
	
	
	

}
