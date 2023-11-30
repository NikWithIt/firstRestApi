package ru.zavialov.restApiProject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.zavialov.restApiProject.models.Sensor;

public class MeasurementDto {
	@NotNull
	@Min(value = -100, message = "Value must be greater than -100")
	@Max(value = 100, message = "Value must be less than 100")
	private int value;
	
	@NotNull(message = "Please, enter the rainig value")
	private boolean raining;
	
	@NotNull(message = "Warning! System can't find sensor")
	private Sensor sensorDtO;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isRaining() {
		return raining;
	}

	public void setRaining(boolean raining) {
		this.raining = raining;
	}

	public Sensor getSensor() {
		return sensorDtO;
	}

	public void setSensor(SensorDto sensorDto) {
		this.sensorDtO = sensorDtO;
	}
	
	
	
	
	
}
