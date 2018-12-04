package com.etsmtl.codecrusade.controllers;

import com.etsmtl.codecrusade.entities.Report;
import com.etsmtl.codecrusade.model.CodeValidationReport;
import com.etsmtl.codecrusade.model.User;
import com.etsmtl.codecrusade.service.ReportService;
import com.etsmtl.codecrusade.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Controller
public class UserController implements UsersApi {

    private ReportService reportService;
    private ModelMapper modelMapper;
    private UserService userService;

    @Override
    public ResponseEntity<List<CodeValidationReport>> userReportsRead(Integer userId) {
        return ResponseEntity.ok(StreamSupport.stream(reportService.findAllReportsForUser(userId).spliterator(), false)
                                              .map(this::convertToDto)
                                              .collect(toList()));
    }

    @Autowired
    public UserController(UserService userService, ReportService reportService, ModelMapper modelMapper) {
        this.userService = userService;
        this.reportService = reportService;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseEntity<List<User>> usersBrowse() {
        return ResponseEntity.ok(StreamSupport.stream(userService.findAll().spliterator(), false)
                                              .map(this::convertToDto)
                                              .collect(toList()));
    }

    @Override
    public ResponseEntity<User> usersAdd(@Valid User user) {
        return userService.createUser(convertToEntity(user))
                          .map(this::convertToDto)
                          .map(created -> ResponseEntity.created(
                                  UriComponentsBuilder.fromPath("/users/{id}").buildAndExpand(created.getId()).toUri())
                                                        .body(created))
                          .orElse(ResponseEntity.badRequest().build());
    }

    @Override
    public ResponseEntity<User> usersRead(Integer userId) {
        return userService.findById(userId)
                          .map(this::convertToDto)
                          .map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    private User convertToDto(com.etsmtl.codecrusade.entities.security.User user) {
        return modelMapper.map(user, User.class);
    }

    private com.etsmtl.codecrusade.entities.security.User convertToEntity(User dto) {
        return modelMapper.map(dto, com.etsmtl.codecrusade.entities.security.User.class);
    }

    private CodeValidationReport convertToDto(Report report) {
        return modelMapper.map(report, CodeValidationReport.class);
    }
}
