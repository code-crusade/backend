package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.security.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@DiscriminatorValue("student")
@EqualsAndHashCode(callSuper = true)
public class Student extends User {

}
