package ru.zavialov.restApiProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.zavialov.restApiProject.models.Measurement;
import ru.zavialov.restApiProject.models.Sensor;
import ru.zavialov.restApiProject.repository.MeasurementRepository;
import ru.zavialov.restApiProject.repository.SensorRepository;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
	private final MeasurementRepository measurementRepository;

	@Autowired	
	public MeasurementService(MeasurementRepository measurementRepository) {
		this.measurementRepository = measurementRepository;
	}

	public List<Measurement> getMeasurements() {
		return measurementRepository.findAll();
	}

	public long rainyDays() {
		return measurementRepository.countByRaining(true);
	}
	
	@Transactional
	public void createMeasurment(Measurement measurement) {
		measurementRepository.save(measurement);
	}
}
