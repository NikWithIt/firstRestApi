package ru.zavialov.restApiProject.controllers;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;
import ru.zavialov.restApiProject.dto.SensorDto;
import ru.zavialov.restApiProject.models.Sensor;
import ru.zavialov.restApiProject.service.MeasurementService;
import ru.zavialov.restApiProject.service.SensorService;
import ru.zavialov.restApiProject.util.BadRequestException;
import ru.zavialov.restApiProject.util.ResponseError;

@RestController
@RequestMapping("/sensors")
public class SensorController {
	private SensorService sensorService;
	
	@Autowired
	public SensorController(SensorService sensorService) {
		super();
		this.sensorService = sensorService;
	}
	
	@PostMapping()
	public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid SensorDto sensorDto, 
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> errors = bindingResult.getFieldErrors();
			
			for (FieldError error: errors) {
				sb.append(error.getField());
				sb.append(" - ");
				sb.append(error.getDefaultMessage());
				sb.append(";");
			}
			throw new BadRequestException(sb.toString());
		}
		sensorService.createSensor(dtoToSensor(sensorDto));
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	private Sensor dtoToSensor(SensorDto sensorDto) {
		ModelMapper mapper = new ModelMapper();
		Sensor sensor = mapper.map(sensorDto, Sensor.class);
		return sensor;
	}
	
	@ExceptionHandler
	private ResponseEntity<ResponseError> exceptionHandler(BadRequestException ex) {
		ResponseError error = new ResponseError(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
	
}
