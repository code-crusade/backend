package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.Exercise;
import com.etsmtl.codecrusade.repository.ExerciseRepository;
import com.etsmtl.codecrusade.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private ExerciseRepository exerciseRepository;
    private JdbcMutableAclService aclService;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, JdbcMutableAclService aclService) {
        this.exerciseRepository = exerciseRepository;
        this.aclService = aclService;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Exercise> getExerciseFromId(Integer id) {
        return exerciseRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Exercise> createExercise(Exercise exercise) {
        Exercise created = exerciseRepository.save(exercise);
        if (created.getId() != null) {
            // Prepare the information we'd like in our access control entry (ACE)
            ObjectIdentity oi = new ObjectIdentityImpl(Exercise.class, exercise.getId());
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

}
