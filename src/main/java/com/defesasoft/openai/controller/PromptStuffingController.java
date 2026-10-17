package com.defesasoft.openai.controller;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PromptStuffingController {

    private final ChatClient chatClient;

    public PromptStuffingController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

@Value("classpath:/promptTemplates/systemPromptTemplate.st")
Resource systemPromptTemplateResource;

    @GetMapping("/prompt-stuffing")
    public String emailresponse(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .system(systemPromptTemplateResource)
                .user(message)
                .call().content();
    }

}
