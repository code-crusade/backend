package com.etsmtl.codecrusade.service;

import com.etsmtl.codecrusade.entities.ClassGroup;

import javax.validation.constraints.NotNull;
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
	Optional<ClassGroup> createGroup(@NotNull ClassGroup classGroup) throws IdSpecifiedException;

	/**
	 * Updates a group with provided values.
	 *
	 * @param classGroup the {@link ClassGroup} to update
	 * @return the updated {@link ClassGroup}
	 */
	Optional<ClassGroup> saveGroup(@NotNull ClassGroup classGroup) throws IdNotSpecifiedException;

	/**
	 * Finds a list of all groups.
	 *
	 * @return a list of all groups
	 */
	Iterable<ClassGroup> findAllGroups();

	/**
	 * An exception that indicates an updated group has no id indicated.
	 */
	class IdNotSpecifiedException extends RuntimeException {

	}

	/**
	 * An exception that indicates a group needed an id but none was provided.
	 */
	class IdSpecifiedException extends RuntimeException {

	}

}
