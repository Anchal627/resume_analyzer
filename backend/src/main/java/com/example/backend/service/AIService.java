package com.example.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIService {

    @Value("${groq.api.key}")
    private String API_KEY;

    public String getAISuggestions(String text) {
        String url = "https://api.groq.com/openai/v1/chat/completions";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();

        request.put("model", "openai/gpt-oss-20b");

        List<Map<String, String>> messages = new ArrayList<>();

        Map<String, String> systemMsg = new HashMap<>();
        systemMsg.put("role", "system");
        systemMsg.put("content", "You are a professional resume reviewer.");

        Map<String, String> userMsg = new HashMap<>();
        userMsg.put("role", "user");
        userMsg.put(
            "content",
            "Analyze this resume and give ONLY 5 short bullet point suggestions.\n" +
            "- Each point must be maximum 12 words.\n" +
            "- Be concise and direct.\n" +
            "- No explanations, no paragraphs.\n\n" +
            text
        );

        messages.add(systemMsg);
        messages.add(userMsg);

        request.put("messages", messages);

        HttpEntity<Map<String, Object>> entity =
            new HttpEntity<>(request, headers);

        ResponseEntity<Map> response =
            restTemplate.postForEntity(url, entity, Map.class);

        Map body = response.getBody();

        if (body == null || body.get("choices") == null) {
            throw new RuntimeException("Invalid response from Groq API");
        }

        Map choice = (Map) ((List) body.get("choices")).get(0);
        Map message = (Map) choice.get("message");

        return message.get("content").toString();
    }
}