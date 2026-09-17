package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.model.ResumeResponse;
import com.example.backend.util.ResumeAnalyzer;
import com.example.backend.util.ResumeParser;

@Service
public class ResumeService {
  @Autowired
  private AIService aiService;
 public ResumeResponse uploadResume(MultipartFile file){
     String text=ResumeParser.extractText(file);
     List<String> skills=ResumeAnalyzer.extractSkills(text);
    String email=ResumeAnalyzer.extractEmail(text);
    
    // return "Extracted Email: " + email + "\nExtracted Skills: " + String.join(", ", skills);

     
    int score=ResumeAnalyzer.calaculateScore(skills, text);
    // return "Score is: " + score;
  
//     List<String> suggestions=ResumeAnalyzer.generateSuggestion(text, score, skills);
     String aiSuggestions=aiService.getAISuggestions(text);

    //returning response
    return new ResumeResponse(email, skills, score, aiSuggestions);
 }
}
