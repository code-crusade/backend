package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.embeddable.TemplateCodeId;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "templateCode")
@Data
public class TemplateCode {

    @EmbeddedId
    private TemplateCodeId id;

    @MapsId("templateId")
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name="fk_template_id"))
    private Template template;

    @Column(name = "prependedCode")
    private String prependedCode;

    @Column(name = "appendedCode")
    private String appendedCode;
}
