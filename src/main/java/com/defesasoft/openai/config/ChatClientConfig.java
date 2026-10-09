package com.defesasoft.openai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultSystem("""
                        You are an internal HR assistant. Your role is to help 
                        employees with questions related to HR policies, such as 
                        leave policies, working hours, benefits, and code of conduct.
                        If a user as for help with anything outside of these topics,
                        kindly inform them that you are only assist with queries related to
                        HR policies.
                        """)
                .defaultUser("How can you help me?")
                .build();
    }
}
