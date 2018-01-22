package com.epic951.utilities;

import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class HTTPUtilities {

	public static final MediaType JSON_CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static final ResponseEntity<String> handleResponse(String status) throws JSONException {
		ResponseEntity<String> response = null;
		JSONObject json = new JSONObject();
		switch (status) {
		case "Success":
			response = new ResponseEntity<String>(json.put("status", "Success").toString(), HttpStatus.OK);
			break;
		case "Failure":
			response = new ResponseEntity<String>(json.put("status", "Failure").toString(), HttpStatus.BAD_REQUEST);
			break;
		default:
			break;
		}
		return response;
	}
}
