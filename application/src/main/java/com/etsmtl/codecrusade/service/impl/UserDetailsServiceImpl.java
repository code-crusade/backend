package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.repository.UserRepository;
import com.etsmtl.codecrusade.service.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                             .map(UserPrincipal::new)
                             .orElseThrow(() -> new UsernameNotFoundException("Could not find user with name : " + username));
    }
}
