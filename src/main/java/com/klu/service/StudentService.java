package com.klu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.dto.StudentRequestDTO;
import com.klu.dto.StudentResponseDTO;
import com.klu.model.Student;
import com.klu.repo.StudentRepo;

@Service
public class StudentService {
  @Autowired
  public StudentRepo studentRepo;
  @Autowired
  public ModelMapper modelMapper;
  
  //create student
  public StudentResponseDTO addStudent(StudentRequestDTO reqdto) {
    //step-1: convert dto to entity
    Student entity = modelMapper.map(reqdto, Student.class);
    //step-2: assign default password in the entity
    entity.setPassword("klu123");
    
    return modelMapper.map(studentRepo.save(entity),StudentResponseDTO.class);
  }
  
  //Read all student details
  public List<StudentResponseDTO> getAllStudents() {
	    List<Student> list = studentRepo.findAll();
	    return list.stream()
	               .map(student -> modelMapper.map(student, StudentResponseDTO.class))
	               .collect(Collectors.toList());
	}
  
  //Read Specific student
  public StudentResponseDTO getStudentById(long id) {
	  Student s = studentRepo.findById(id).orElseThrow();
	  return modelMapper.map(s, StudentResponseDTO.class);
  }
  
  //delete specific student
  public String deleteStudent(Long id) {
	  studentRepo.findById(id).orElseThrow();
	  studentRepo.deleteById(id);
	  return "Student Deleted Successfully";
  }
  
  //update Student
  public StudentResponseDTO updateStudent(Long id, StudentRequestDTO s) {
	  Student existedStudent = studentRepo.findById(id).orElseThrow();
	  existedStudent.setName(s.getName());
	  existedStudent.setEmail(s.getEmail());
	  existedStudent.setCourse(s.getCourse());
	  existedStudent.setBranch(s.getBranch());
	  existedStudent.setFees(s.getFees());
	  Student updated = studentRepo.save(existedStudent);
	  return modelMapper.map(updated, StudentResponseDTO.class);

  }
}
