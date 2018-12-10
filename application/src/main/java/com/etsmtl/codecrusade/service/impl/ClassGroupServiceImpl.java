package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.ClassGroup;
import com.etsmtl.codecrusade.repository.ClassGroupRepository;
import com.etsmtl.codecrusade.service.ClassGroupService;
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
import java.util.Optional;

/**
 * Service implementation for ClassGroup-related operations.
 */
@Service
@Validated
public class ClassGroupServiceImpl implements ClassGroupService {

	private ClassGroupRepository groupRepository;
	private JdbcMutableAclService aclService;

	@Autowired
	public ClassGroupServiceImpl(ClassGroupRepository groupRepository, JdbcMutableAclService aclService) {
		this.groupRepository = groupRepository;
		this.aclService = aclService;
	}

	@Transactional
	@Override
	public Optional<ClassGroup> createGroup(@NotNull ClassGroup classGroup) throws IdSpecifiedException {
		if (classGroup.getId() != null) {
			throw new IdSpecifiedException();
		}

        ClassGroup created = groupRepository.save(classGroup);

		if(created.getId() != null){
            // Prepare the information we'd like in our access control entry (ACE)
            ObjectIdentity oi = new ObjectIdentityImpl(ClassGroup.class, created.getId());
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
            acl.insertAce(acl.getEntries().size(), BasePermission.DELETE, sid, true);
            aclService.updateAcl(acl);
            return Optional.of(created);
        } else {
		    return Optional.empty();
        }
    }

    @Transactional
	@Override
	public Optional<ClassGroup> saveGroup(@NotNull ClassGroup classGroup) throws IdNotSpecifiedException {
		if (classGroup.getId() == null) {
			throw new IdNotSpecifiedException();
		}
		return Optional.of(groupRepository.save(classGroup));
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<ClassGroup> findAllGroups() {
		return groupRepository.findAll();
	}
}
