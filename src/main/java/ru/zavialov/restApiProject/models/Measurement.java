package ru.zavialov.restApiProject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "measurement")
public class Measurement {
	
	@Id
	@Column(name = "measurement_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "value")
	@NotNull
	@Min(value = -100, message = "Value must be greater than minus 100")
	@Max(value = 100, message = "Value must be less than -100")
	private int value;
	
	
	@Column(name = "raining")
	@NotNull
	private boolean raining;
	
	@ManyToOne
	@JoinColumn(name = "sensor_id", referencedColumnName = "sensor_id")
	private Sensor sensor;
	
	

	public Measurement() {
		super();
	}
	

	public Measurement(int id,
			@NotNull @Min(value = -100, message = "Value must be greater than minus 100") @Max(value = 100, message = "Value must be less than -100") int value,
			@NotNull boolean raining, Sensor sensor) {
		super();
		this.id = id;
		this.value = value;
		this.raining = raining;
		this.sensor = sensor;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}
	
	
}
