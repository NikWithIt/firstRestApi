package ru.zavialov.restApiProject.util;

public class BadRequestException extends RuntimeException {
	private String msg;

	public BadRequestException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
