package com.test.footest.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="foo")
@Getter
@Setter
public class Foo {
	@Id
	private Long id;
	@Column(name="bar")
	@Size(min=0, max=255)
	private String baz;
}
