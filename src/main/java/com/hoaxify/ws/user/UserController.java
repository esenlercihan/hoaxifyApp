package com.hoaxify.ws.user;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.util.GenericResponse;

@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	@PostMapping("/api/1.0/users")
	@ResponseStatus(code = HttpStatus.OK)
	public GenericResponse createUser(@Valid @RequestBody User user) {
		log.info(user.toString());
		userService.save(user);
		return new GenericResponse("User Created");
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleValidationException(MethodArgumentNotValidException exception) {
		ApiError error = new ApiError(400, "Validation Error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>();
		for(FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		error.setValidationErrors(validationErrors);
		return error;
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
		ApiError error = new ApiError(400, "Validation Error", "/api/1.0/users");
		Map<String, String> validationErrors = new HashMap<>();
		validationErrors.put("username", "Username must be unique");
		error.setValidationErrors(validationErrors);
		return error;
	}
	
}
