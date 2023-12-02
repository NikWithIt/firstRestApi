package ru.zavialov.restApiProject.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "sensor")
public class Sensor {
	@Id
	@Column(name = "sensor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	@NotNull
	@Size(min = 3, max = 30, message = "Sensors name not valid (3 < size < 30")
	private String name;
	
	
	@OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
	private List <Measurement> measures;


	public Sensor(int id,
			@NotNull @Size(min = 3, max = 30, message = "Sensors name not valid (3 < size < 30") String name,
			List<Measurement> measures) {
		super();
		this.id = id;
		this.name = name;
		this.measures = measures;
	}


	public Sensor() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Measurement> getMeasures() {
		return measures;
	}


	public void setMeasures(List<Measurement> measures) {
		this.measures = measures;
	}


	@Override
	public String toString() {
		return "Sensor [id=" + id + ", name=" + name + "]";
	}
	
	
}
