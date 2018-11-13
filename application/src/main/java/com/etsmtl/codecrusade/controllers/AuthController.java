package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.model.Credentials;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Controller for auth. As everything is passthrough, this might be removed.
 */
public class AuthController implements AuthApi {

	@Override
	public ResponseEntity<Void> authCasloginGet(@NotNull @Valid String ticket) {
		return null;
	}

	@Override
	public ResponseEntity<Void> authLoginPost(@Valid Credentials credentials) {
		return null;
	}
}
