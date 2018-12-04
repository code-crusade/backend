package com.etsmtl.codecrusade.service.impl;

import com.etsmtl.codecrusade.entities.Submission;
import com.etsmtl.codecrusade.repository.ExerciseRepository;
import com.etsmtl.codecrusade.repository.SubmissionRepository;
import com.etsmtl.codecrusade.repository.UserRepository;
import com.etsmtl.codecrusade.service.ExerciseService.ExerciseNotFoundException;
import com.etsmtl.codecrusade.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Service
@Validated
public class SubmissionServiceImpl implements SubmissionService {

    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private SubmissionRepository submissionRepository;
    private JdbcMutableAclService aclService;

    @Autowired
    public SubmissionServiceImpl(SubmissionRepository submissionRepository, ExerciseRepository exerciseRepository,
                                 UserRepository userRepository, JdbcMutableAclService aclService) {
        this.submissionRepository = submissionRepository;
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.aclService = aclService;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Submission> getSubmissionByExerciseForAuthenticatedUser(Integer exerciseId, Integer submissionId) {
        return submissionRepository.findByExercise_IdAndId(exerciseId, submissionId);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Submission> getAllSubmissionsByExercise(Integer exerciseId) {
        return submissionRepository.findAllByExercise_Id(exerciseId);
    }

    @Transactional
    @Override
    public Optional<Submission> createSubmissionForExercise(Integer exerciseId, Submission submission)
            throws UserNotAllowedException, ExerciseNotFoundException {
        Objects.requireNonNull(exerciseId);
        Objects.requireNonNull(submission);
        // find exercise or throw
        exerciseRepository.findById(exerciseId).orElseThrow(ExerciseNotFoundException::new);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // A principal should be in context
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(currentPrincipalName).map(user -> {
                    // ensure no submission exists
                    if (submissionRepository.countByExercise_IdAndUser_Id(exerciseId, user.getId()) == 0) {
                        Submission created = saveSubmission(submission);
                        if(created.getId() != null){
                            // Prepare the information we'd like in our access control entry (ACE)
                            ObjectIdentity oi = new ObjectIdentityImpl(Submission.class, submission.getId());
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
                            return created;
                        } else {
                            return null;
                        }
                    }
                    return null;
                }
        );
    }

    /**
     * Saves submission, with validation guard.
     *
     * @param submission the submission to save
     * @return the saved submission, with a generated id if successful
     */
    private Submission saveSubmission(@Valid Submission submission) {
        return submissionRepository.save(submission);
    }
}
