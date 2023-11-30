package ru.zavialov.restApiProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zavialov.restApiProject.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
	public long countByRaining(boolean isRainy);

}
