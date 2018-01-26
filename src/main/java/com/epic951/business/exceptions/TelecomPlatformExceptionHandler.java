package com.epic951.business.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.epic951.utilities.APIError;
import com.epic951.utilities.HTTPUtilities;

@ControllerAdvice
public class TelecomPlatformExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { OperatorRequirementsViolationException.class })
	protected ResponseEntity<Object> handleOperatorRequirementsViolationException(
			OperatorRequirementsViolationException ex) {
		APIError apiError = new APIError(HttpStatus.NOT_ACCEPTABLE, ex.getLocalizedMessage(), ex.getErrors());
		return ResponseEntity.status(apiError.getStatus()).contentType(HTTPUtilities.JSON_CONTENT_TYPE).body(apiError);
	}

	@ExceptionHandler(value = { MissingRequiredValueException.class })
	protected ResponseEntity<Object> handleMissingOperatorNameException(MissingRequiredValueException ex) {
		APIError apiError = new APIError(HttpStatus.PRECONDITION_FAILED, ex.getLocalizedMessage(), ex.getErrors());
		return ResponseEntity.status(apiError.getStatus()).contentType(HTTPUtilities.JSON_CONTENT_TYPE).body(apiError);
	}

}
