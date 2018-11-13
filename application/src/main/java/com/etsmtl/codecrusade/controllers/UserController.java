package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.model.User;
import com.etsmtl.codecrusade.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Controller
public class UserController implements UsersApi {

	private ModelMapper modelMapper;
	private UserService userService;

	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<List<User>> usersBrowse() {
		return ResponseEntity.ok(StreamSupport.stream(userService.findAll().spliterator(), false)
											  .map(this::convertToDto)
											  .collect(toList()));
	}

	@Override
	public ResponseEntity<User> usersAdd(@Valid User user) {
		return userService.createUser()
						  .map(this::convertToDto)
						  .map(created -> ResponseEntity.created(
								  UriComponentsBuilder.fromPath("/users/{id}").buildAndExpand(created.getId()).toUri())
														.body(created))
						  .orElse(ResponseEntity.badRequest().build());
	}

	@Override
	public ResponseEntity<User> usersRead(Integer userId) {
		return userService.findById(userId)
						  .map(this::convertToDto)
						  .map(ResponseEntity::ok)
						  .orElse(ResponseEntity.notFound().build());
	}

	private User convertToDto(com.etsmtl.codecrusade.entities.security.User user) {
		return modelMapper.map(user, User.class);
	}
}
