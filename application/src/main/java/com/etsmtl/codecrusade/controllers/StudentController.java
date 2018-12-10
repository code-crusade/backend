package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.entities.Student;
import com.etsmtl.codecrusade.model.StudentModel;
import com.etsmtl.codecrusade.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Controller
public class StudentController implements StudentsApi{

    private StudentService studentService;

    private ModelMapper modelMapper;

    @Autowired
    public StudentController(StudentService studentService, ModelMapper modelMapper)
    {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }
    @Override
    public ResponseEntity<List<StudentModel>> studentsRead(Integer userId) {
        return ResponseEntity.ok(StreamSupport.stream(studentService.findAllStudents().spliterator(),false).map(this::toDto).collect(toList()));
    }

    private StudentModel toDto(Student student) {
        return modelMapper.map(student,StudentModel.class);
    }
}
