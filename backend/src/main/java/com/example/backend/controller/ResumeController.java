package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.backend.model.ResumeResponse;
import com.example.backend.service.ResumeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
   @PostMapping("/upload")
   public ResumeResponse uploadResume(@RequestParam("file") MultipartFile file) {
     return resumeService.uploadResume(file);
   }
}
