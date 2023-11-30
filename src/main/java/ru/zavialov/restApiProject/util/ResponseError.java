package ru.zavialov.restApiProject.util;

public class ResponseError extends RuntimeException{
	private String msg;

	public ResponseError(String msg) {
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
