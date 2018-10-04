package com.etsmtl.codecrusade.runner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Contains the result of an execution.
 */
@Data
@AllArgsConstructor
@Builder
public class ExecutionResult {
	private String          message;
	private ExecutionStatus status;

	public enum ExecutionStatus {
		SUCCESS(0), FAILURE(1);
		private final int val;

		private ExecutionStatus(int val) {
			this.val = val;
		}
	}
}
