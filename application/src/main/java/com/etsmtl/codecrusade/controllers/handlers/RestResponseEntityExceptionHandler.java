package com.etsmtl.codecrusade.controllers.handlers;

import com.etsmtl.codecrusade.service.ClassGroupService.IdNotSpecifiedException;
import com.etsmtl.codecrusade.service.ExerciseService.ExerciseNotFoundException;
import com.etsmtl.codecrusade.service.SubmissionService.UserNotAllowedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class contains handler methods for all exceptions
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Internal error";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(UserNotAllowedException.class)
	protected ResponseEntity<Void> handleUserNotAllowed(UserNotAllowedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	@ExceptionHandler(ExerciseNotFoundException.class)
	protected ResponseEntity<Void> handleExerciseNotFound(ExerciseNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(IdNotSpecifiedException.class)
	protected ResponseEntity<Void> handleIdShouldNotHaveBeenSpecifiedException(IdNotSpecifiedException ex) {
		return ResponseEntity.badRequest().build();
	}
}