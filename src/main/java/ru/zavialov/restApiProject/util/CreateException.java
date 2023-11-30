package ru.zavialov.restApiProject.util;

public class CreateException extends RuntimeException {
	private String msg;

	public CreateException(String msg) {
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
