package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.ClassGroup;

import java.util.List;
import java.util.Optional;

/**
 * Operations pertaining to ClassGroup objects.
 */
public interface ClassGroupService {

	/**
	 * Creates provided group and returns it with an assigned id.
	 *
	 * @param classGroup the {@link ClassGroup} to create
	 * @return the created group, with an id assigned
	 */
	Optional<ClassGroup> createGroup(ClassGroup classGroup);

	/**
	 * Updates a group with provided values.
	 *
	 * @param id         the id of the existing ClassGroup
	 * @param classGroup the {@link ClassGroup} to update
	 * @return the updated {@link ClassGroup}
	 */
	Optional<ClassGroup> updateGroup(Integer id, ClassGroup classGroup);

	/**
	 * Finds a list of all groups.
	 *
	 * @return a list of all groups
	 */
	List<ClassGroup> findAllGroups();

}
