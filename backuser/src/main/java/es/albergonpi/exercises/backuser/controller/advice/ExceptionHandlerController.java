package es.albergonpi.exercises.backuser.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import es.albergonpi.exercises.backuser.exception.NotFoundException;
import es.albergonpi.exercises.backuser.exception.ResourceRepeatedException;

@Controller
@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(NotFoundException.class)
	ResponseEntity<String> resourceNotFoundError(NotFoundException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceRepeatedException.class)
	ResponseEntity<String> resourceRepeatedError(ResourceRepeatedException ex) {
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
