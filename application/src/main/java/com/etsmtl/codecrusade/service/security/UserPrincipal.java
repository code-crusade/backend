package com.etsmtl.codecrusade.service.security;

import com.etsmtl.codecrusade.entities.security.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.etsmtl.codecrusade.util.Utilities.nullable;
import static java.util.stream.Collectors.toList;

public class UserPrincipal implements UserDetails {
    @Getter
    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserAuthority> userAuthorities = nullable(user.getAuthorities()).orElseGet(Collections::emptyList);
        List<GroupAuthority> groupAuthorities = nullable(user.getMemberships()).map(memberships -> memberships.stream()
                                                                                                             .map(GroupMember::getGroup)
                                                                                                             .map(Group::getGroupAuthorities)
                                                                                                             .flatMap(Collection::stream)
                                                                                                             .collect(toList()))
                                                                              .orElseGet(Collections::emptyList);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(userAuthorities);
        authorities.addAll(groupAuthorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
