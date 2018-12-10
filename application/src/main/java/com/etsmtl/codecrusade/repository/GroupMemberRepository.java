package com.etsmtl.codecrusade.repository;

import com.etsmtl.codecrusade.entities.security.GroupMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupMemberRepository extends CrudRepository<GroupMember,Long> {
    Optional<GroupMember> findGroupMemberByGroup_IdAndUser_Id(Long groupId, Integer UserId);
}
