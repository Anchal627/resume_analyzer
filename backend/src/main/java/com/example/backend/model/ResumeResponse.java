package com.example.backend.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResumeResponse {
  private String email;
  private List<String> skills;
  private int score;
  public String suggestions;
}
