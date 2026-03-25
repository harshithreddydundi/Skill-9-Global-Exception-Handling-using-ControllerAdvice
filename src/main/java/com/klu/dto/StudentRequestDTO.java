package com.klu.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {
  @NotEmpty(message = "Student name is required")
  private String name;
  @Email(message = "Email must have proper format")
  private String email;
  @NotBlank(message = "Branch must not be null")
  private String branch;
  @NotBlank(message = "Course must not be null")
  private String course;
  @Positive(message = "Fees must be positive number")
  private double fees;
}
