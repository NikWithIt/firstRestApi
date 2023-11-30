package ru.zavialov.restApiProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.zavialov.restApiProject.models.Sensor;
import ru.zavialov.restApiProject.repository.SensorRepository;

@Service
@Transactional(readOnly = true)
public class SensorService {
	private final SensorRepository sensorRepository;

	@Autowired
	public SensorService(SensorRepository sensorRepository) {
		super();
		this.sensorRepository = sensorRepository;
	}
	
	@Transactional
	public void createSensor(Sensor sensor) {
		sensorRepository.save(sensor);
	}
	
	public Sensor findByName(String name) {
		return sensorRepository.findByName(name);
	}
	
	
	
	
	
	
	
}
