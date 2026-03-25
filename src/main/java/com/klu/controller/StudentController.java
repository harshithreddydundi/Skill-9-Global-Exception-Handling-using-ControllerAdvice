package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.klu.dto.StudentRequestDTO;
import com.klu.dto.StudentResponseDTO;
import com.klu.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/stud")
@CrossOrigin("*")
public class StudentController {
  @Autowired
  private StudentService studentService;
  
  @PostMapping("/addStudent")
  public ResponseEntity<StudentResponseDTO> addStudent(@Valid @RequestBody StudentRequestDTO dto){
    return new ResponseEntity<>(studentService.addStudent(dto),HttpStatus.CREATED);
  }
  
  @GetMapping("/getAllStudents")
  public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
	  return ResponseEntity.ok(studentService.getAllStudents());
  }
  @GetMapping("/getStudentById/{id}")
  public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id){
	  return ResponseEntity.ok(studentService.getStudentById(id));
  }
  @DeleteMapping("/deleteStudent/{id}")
  public ResponseEntity<String> deleteStudent(@PathVariable Long id){
	  return ResponseEntity.ok(studentService.deleteStudent(id));
  }
  
  @PutMapping("/updateStudent/{id}")
  public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id,
                                                          @Valid @RequestBody StudentRequestDTO dto) {
      StudentResponseDTO updatedStudent = studentService.updateStudent(id, dto);
      return ResponseEntity.ok(updatedStudent);
  }
}
