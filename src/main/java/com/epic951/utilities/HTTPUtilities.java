package com.epic951.utilities;

import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.epic951.business.exceptions.MissingRequiredValueException;
import com.epic951.business.exceptions.OperatorRequirementsViolationException;

public class HTTPUtilities {

	public static final MediaType JSON_CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private static String errorMessage;
	private static ArrayList<String> errors = new ArrayList<>();

	public static final ResponseEntity<String> handleResponse(String status)
			throws JSONException, OperatorRequirementsViolationException, MissingRequiredValueException {
		ResponseEntity<String> response = null;
		JSONObject json = new JSONObject();
		switch (status.toLowerCase()) {
		case "success":
			response = new ResponseEntity<String>(json.put("status", "Success").toString(), HttpStatus.OK);
			break;
		case "failure":
			if (errors.get(0).toLowerCase().contains("empty")) {
				throw new MissingRequiredValueException(errorMessage, errors);
			} else {
				throw new OperatorRequirementsViolationException(errorMessage, errors);
			}
		default:
			break;
		}
		return response;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

	public static void setErrors(ArrayList<String> validationErrors) {
		errors = validationErrors;
	}

	public static void setErrors(String error) {
		errors.add(error);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public static void setErrorMessage(String message) {
		errorMessage = message;
	}
}
