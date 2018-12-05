package com.etsmtl.codecrusade.entities;

import com.etsmtl.codecrusade.entities.embeddable.TemplateCodeId;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "templateCode")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class TemplateCode {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private TemplateCodeId id;

    @Version
    @Setter(AccessLevel.NONE)
    private Long version;

    @MapsId("templateId")
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_template_id"))
    private Template template;

    @Column(name = "prependedCode")
    @Size(max = 1000)
    private String prependedCode;

    @Column(name = "appendedCode")
    @Size(max = 1000)
    private String appendedCode;

    @Builder
    public TemplateCode(TemplateCodeId id, Template template, @Size(max = 1000) String prependedCode, @Size(
            max = 1000) String appendedCode) {
        this.id = id;
        this.template = template;
        this.prependedCode = prependedCode;
        this.appendedCode = appendedCode;
    }
}
