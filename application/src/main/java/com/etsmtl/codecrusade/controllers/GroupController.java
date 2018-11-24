package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.entities.ClassGroup;
import com.etsmtl.codecrusade.model.Group;
import com.etsmtl.codecrusade.service.ClassGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Controller
public class GroupController implements GroupsApi {

	private ClassGroupService groupService;
	private ModelMapper       modelMapper;

	@Autowired
	public GroupController(ClassGroupService groupService, ModelMapper modelMapper) {
		this.groupService = groupService;
		this.modelMapper = modelMapper;
	}

	@Override
	public ResponseEntity<Group> groupsAdd(@Valid Group group) {
		return groupService.createGroup(convertToEntity(group))
						   .map(created -> ResponseEntity.created(UriComponentsBuilder.fromPath("/groups/{id}")
																					  .buildAndExpand(created.getId())
																					  .toUri())
														 .body(convertToDto(created)))
						   .orElse(ResponseEntity.badRequest().build());
	}

	@Override
	public ResponseEntity<Group> groupsEdit(@Valid Group group) {
		return groupService.saveGroup(convertToEntity(group))
						   .map(created -> ResponseEntity.ok(convertToDto(created)))
						   .orElse(ResponseEntity.badRequest().build());
	}

	@Override
	public ResponseEntity<List<Group>> groupsBrowse() {
		return ResponseEntity.ok(StreamSupport.stream(groupService.findAllGroups().spliterator(), false)
											  .map(this::convertToDto)
											  .collect(toList()));
	}

	private ClassGroup convertToEntity(Group group) {
		return modelMapper.map(group, ClassGroup.class);
	}

	private Group convertToDto(ClassGroup classGroup) {
		return modelMapper.map(classGroup, Group.class);
	}
}
