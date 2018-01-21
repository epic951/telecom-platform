package com.epic951.utilities;

import java.nio.charset.Charset;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HTTPUtilities {

	public static final MediaType JSON_CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static final ResponseEntity<String> handleResponse(String status) {
		ResponseEntity<String> response = null;
		switch (status) {
		case "Success":
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			break;
		case "Failure":
			response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
			break;
		default:
			break;
		}
		return response;
	}
}
