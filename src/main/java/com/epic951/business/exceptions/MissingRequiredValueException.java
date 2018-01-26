package com.epic951.business.exceptions;

import java.util.ArrayList;

public class MissingRequiredValueException extends Exception {

	private static final long serialVersionUID = 1524335270338839462L;
	private String message;
	private ArrayList<String> errors;

	public MissingRequiredValueException(String message, ArrayList<String> errors) {
		this.message = message;
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<String> errors) {
		this.errors = errors;
	}

}
