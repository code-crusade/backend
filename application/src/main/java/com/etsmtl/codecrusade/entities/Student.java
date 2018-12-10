package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.security.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@DiscriminatorValue("student")
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

    @OneToMany(mappedBy = "user")
    private List<Submission> submissions;
}
