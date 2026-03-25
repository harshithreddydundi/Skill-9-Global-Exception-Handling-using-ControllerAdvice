package com.klu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDTO {
  private Long id;
  private String name;
  private String email;
  private String branch;
  private String course;
  private double fees;
}
