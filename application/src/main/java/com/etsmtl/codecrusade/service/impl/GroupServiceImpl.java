package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.ClassGroup;
import com.etsmtl.codecrusade.repository.ClassGroupRepository;
import com.etsmtl.codecrusade.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Service implementation for Group-related operations.
 */
@Service
@Validated
public class GroupServiceImpl implements ClassGroupService {

	private ClassGroupRepository groupRepository;

	@Autowired
	public GroupServiceImpl(ClassGroupRepository groupRepository) {
		this.groupRepository = groupRepository;
	}

	@Override
	public Optional<ClassGroup> createGroup(@NotNull ClassGroup classGroup) throws IdSpecifiedException {
		if (classGroup.getId() != null) {
			throw new IdSpecifiedException();
		}
		return Optional.of(groupRepository.save(classGroup));
	}

	@Override
	public Optional<ClassGroup> saveGroup(@NotNull ClassGroup classGroup) throws IdNotSpecifiedException {
		if (classGroup.getId() == null) {
			throw new IdNotSpecifiedException();
		}
		return Optional.of(groupRepository.save(classGroup));
	}

	@Override
	public Iterable<ClassGroup> findAllGroups() {
		return groupRepository.findAll();
	}
}
