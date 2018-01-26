package com.epic951.utilities;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class APIError {

	private HttpStatus status;
	private int code;
	private String message;
	private List<String> errors;

	public APIError(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.code = status.value();
		this.message = message;
		this.errors = errors;
	}

	public APIError(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.code = status.value();
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
