package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.security.GroupMember;
import com.etsmtl.codecrusade.entities.security.User;
import com.etsmtl.codecrusade.repository.GroupMemberRepository;
import com.etsmtl.codecrusade.repository.GroupRepository;
import com.etsmtl.codecrusade.repository.UserRepository;
import com.etsmtl.codecrusade.service.UserService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for user-based operations.
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private GroupRepository groupRepository;
    private GroupMemberRepository groupMemberRepository;
    private JdbcMutableAclService aclService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GroupRepository groupRepository, GroupMemberRepository groupMemberRepository, JdbcMutableAclService aclService) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupMemberRepository = groupMemberRepository;
        this.aclService = aclService;
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<User> createUser(@NotNull User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailExistsException();
        } else {
            User created = userRepository.save(user);
            if (created.getId() == null) {
                // TODO : make more verbose exception and add exception handler
                throw new UserCreationException();
            }
            // Add user self edit right
            // Prepare the information we'd like in our access control entry (ACE)
            ObjectIdentity oi = new ObjectIdentityImpl(User.class, created.getId());
            Sid sid = new PrincipalSid(SecurityContextHolder.getContext().getAuthentication());

            // Create or update the relevant ACL
            MutableAcl acl = null;
            try {
                acl = (MutableAcl) aclService.readAclById(oi);
            } catch (NotFoundException nfe) {
                acl = aclService.createAcl(oi);
            }
            // Now grant some permissions via an access control entry (ACE)
            acl.insertAce(acl.getEntries().size(), BasePermission.READ, sid, true);
            acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, sid, true);
            aclService.updateAcl(acl);

            groupRepository.findGroupByName(UserService.USER_GROUP).ifPresent(group -> {
                List<GroupMember> members = group.getMembers();
                GroupMember groupMember = GroupMember.builder().group(group).user(created).group(group).build();
                groupMemberRepository.save(groupMember);
                if (members != null) {
                    members.add(groupMember);
                    group.setMembers(members);
                } else {
                    group.setMembers(Lists.newArrayList(groupMember));
                }
                groupRepository.save(group);
            });
            return Optional.of(created);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findById(@NotNull Integer id) {
        return userRepository.findById(id);
    }
}
