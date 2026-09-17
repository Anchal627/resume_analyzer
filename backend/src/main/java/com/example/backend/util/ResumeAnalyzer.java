package com.example.backend.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResumeAnalyzer {
  private static final List<String> skills=Arrays.asList("Java","Python","C++","JavaScript","SQL","AWS","Docker","Kubernetes","Git","Linux","Spring Boot","React","MongoDB");
  //extracting the skills from the resume text
  public static List<String> extractSkills(String text){
    List<String> foundSkills=new ArrayList<>();
    for(String skill:skills){
      if(text.toLowerCase().contains(skill.toLowerCase())){
         foundSkills.add(skill);
      }
    }
    return foundSkills;
  }
  //extracting the email from the resume text
  public static String extractEmail(String text){
    Pattern pattern=Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    Matcher matcher=pattern.matcher(text);
    if(matcher.find()){
      return matcher.group(0);
    }
    return "No email found";
  }
  //calculating the score based on the skills and text quality
  public static int calaculateScore(List<String> skills,String text){
    int score=0;
    score+=Math.min(skills.size()*5,40);
    int words=text.split("\\s+").length;
    if(words>400){
      score+=20;
    }
    else if(score>250){
      score+=15;
    }
    else if(score>150){
      score+=10;
    }
    else{
      score+=5;
    }
    if(text.toLowerCase().contains("project")) score+=10;
    if(text.toLowerCase().contains("experience")) score+=10;
    if(text.toLowerCase().contains("education")) score+=10;
    return Math.min(score, 100);
  }


  //generating suggestions based on the score and content
  public static List<String> generateSuggestion(String text,int score,List<String> skills){
    List<String> suggestions=new ArrayList<>();
    String lower=text.toLowerCase();
    if(score<60){
      suggestions.add("Your resume score is low. Try improving content and adding relevant skills.");
    }
    if (skills.size() < 5) {
        suggestions.add("Add more technical skills related to your domain.");
    }
     if (!lower.contains("project")) {
        suggestions.add("Add a Projects section to showcase your work.");
    }
     if (!lower.contains("experience")) {
        suggestions.add("Include experience or internships.");
    }
     if (!lower.contains("education")) {
        suggestions.add("Add your education details clearly.");
    }
      suggestions.add("Use strong action verbs like 'Developed', 'Built', 'Optimized'.");
    suggestions.add("Keep resume concise (1-2 pages).");
return suggestions;
  }
}
