package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.annotation.MessageTemplate;
import com.etsmtl.codecrusade.entities.converters.StringListAttributeConverter;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "title_message_id", foreignKey = @ForeignKey(name = "fk_title_message_id"))
    private Message title;

    @MessageTemplate("exercise.{id}.description")
    @OneToOne
    @JoinColumn(name = "description_message_id", foreignKey = @ForeignKey(name = "fk_desc_message_id"))
    private Message description;

    @Convert(converter = StringListAttributeConverter.class)
    @Column(name = "supportedLanguages")
    private List<String> supportedLanguages = new ArrayList<>();

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
