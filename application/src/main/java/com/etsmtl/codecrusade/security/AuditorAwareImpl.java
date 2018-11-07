package com.etsmtl.codecrusade.security;

import com.etsmtl.codecrusade.service.security.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable(((UserPrincipal) (SecurityContextHolder.getContext()
																		  .getAuthentication()
																		  .getPrincipal())).getUsername());
	}
}
