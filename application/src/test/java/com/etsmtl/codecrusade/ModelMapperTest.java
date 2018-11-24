package com.etsmtl.codecrusade;

import com.etsmtl.codecrusade.repository.ClassGroupRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;

import static org.junit.Assert.fail;

public class ModelMapperTest {

    private ModelMapper modelMapper;

    @Mock
    private ClassGroupRepository classGroupRepository;

    @Before
    public void setModelMapper() {
        this.modelMapper = new ApplicationConfiguration().modelMapper(classGroupRepository);
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
