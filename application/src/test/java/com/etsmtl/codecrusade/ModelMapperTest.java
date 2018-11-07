package com.etsmtl.codecrusade;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;

import static org.junit.Assert.fail;

public class ModelMapperTest {

	private ModelMapper modelMapper;

	@Before
	public void setModelMapper(){
		this.modelMapper = new ApplicationConfiguration().modelMapper();
	}

	@Test
	public void testMapperValidates() {
		try {
			modelMapper.validate();
		} catch (ValidationException e) {
			fail("Modelmapper does not validate : " + e.getErrorMessages());
		}

	}
}
