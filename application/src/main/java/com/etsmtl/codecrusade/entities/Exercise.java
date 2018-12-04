package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.annotation.MessageTemplate;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "exercise")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Integer id;


    @OneToOne
    @JoinColumn(name = "title_message_id",
                foreignKey = @ForeignKey(name = "fk_title_message_id"))
    private Message title;

    @MessageTemplate("exercise.{id}.description")
    @OneToOne
    @JoinColumn(name = "description_message_id",
                foreignKey = @ForeignKey(name = "fk_desc_message_id"))
    private Message description;

    @ElementCollection
    @MapKeyColumn(name = "language")
    @Column(name = "fixture")
    @Size(max = 5000)
    @CollectionTable(name = "exercise_fixtures",
                     joinColumns = @JoinColumn(name = "exercise_id",
                                               foreignKey = @ForeignKey(name = "fk_exercise_id")))
    private Map<String, String> fixtures;

    @OneToOne
    @JoinColumn(name = "exrecise_id",
                foreignKey = @ForeignKey(name = "fk_template_id"))
    private Template template;

    @OneToMany
    @JoinColumn(name = "exercise_id",
                foreignKey = @ForeignKey(name = "fk_exercise_id"))
    private List<ApplicationTestCase> testCases = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
}
