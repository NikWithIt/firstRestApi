package ru.zavialov.restApiProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zavialov.restApiProject.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer>{
	public Sensor findByName(String name);
}
