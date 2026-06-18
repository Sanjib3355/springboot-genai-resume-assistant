package com.sanjib.ai.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjib.ai.api.model.ChatRequest;
import com.sanjib.ai.api.service.GeminiService;
import com.sanjib.ai.api.service.PdfService;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private GeminiService geminiService;

    @GetMapping("/hello")
    public String hello() {

        return "Gemini Project Working";
    }

    @PostMapping
    public String askAI(
            @RequestBody ChatRequest request) {

        String resume =
                pdfService.getResumeText();

        return geminiService.askAI(
                resume,
                request.getQuestion());
    }
}
