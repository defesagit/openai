package com.defesasoft.openai.config;

import com.defesasoft.openai.advisor.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        var options = OpenAiChatOptions.builder().model("gpt-5.4-mini").temperature(0.8);
                //.maxCompletionTokens(10);
        return chatClientBuilder
                .defaultOptions(options)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(),
                        new TokenUsageAuditAdvisor()))
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
