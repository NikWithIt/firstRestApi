package ru.zavialov.restApiProject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SensorDto {
	
	@NotNull
	@Size(min = 3, max = 30, message = "Name must be greater than 3, less than 30")
	private String name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
