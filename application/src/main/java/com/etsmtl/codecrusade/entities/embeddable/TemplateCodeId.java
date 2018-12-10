package com.etsmtl.codecrusade.entities.embeddable;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

/**
 * A composed id class for TemplateCode entities.
 */
@Embeddable
@Data
@NoArgsConstructor
public class TemplateCodeId implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "templateId")
    @Setter(AccessLevel.NONE)
    private Integer templateId;

    @Column(name = "lang")
    private String lang;

    @Builder
    public TemplateCodeId(String lang){
        this.lang = lang;
    }
}
