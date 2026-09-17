package com.example.backend.util;



import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;



public class ResumeParser {
  public static String extractText(MultipartFile file){
    try{
     Tika tika= new Tika();
    String text = tika.parseToString(file.getInputStream());
    // System.out.println("Extracted Text: " + text);
   return text;
    }catch(Exception e){
        System.out.println("Error parsing resume: " + e.getMessage());
        return "Error parsing resume";

    }
   

  }
}
