package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.model.InlineObject;
import com.etsmtl.codecrusade.model.UserPass;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AuthController implements AuthApi {

	@Override
	public ResponseEntity<Void> authCasloginGet(@NotNull @Valid String ticket) {
		return null;
	}

	@Override
	public ResponseEntity<Void> authLoginPost(@Valid UserPass userPass) {
		return null;
	}
}
