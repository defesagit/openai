package com.defesasoft.openai.model.rag;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

//@Component
public class RandomDataLoader {
    private final VectorStore vectorStore;

    public RandomDataLoader(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void loadSentencesIntoVectorStore() {
        List<String> sentences = List.of(
                "The quick brown fox jumps over the lazy dog.",
                "Artificial intelligence is transforming the world.",
                "Spring Boot makes it easy to create stand-alone applications.",
                "Java is a versatile programming language.",
                "Machine learning algorithms can learn from data.",
                "The sun rises in the east and sets in the west.",
                "Cloud computing provides scalable resources on demand.",
                "Data science combines statistics, programming, and domain knowledge.",
                "Natural language processing enables computers to understand human language.",
                "The internet has revolutionized communication and information sharing.",
                "Cybersecurity is crucial for protecting sensitive information.",
                "Blockchain technology enables secure and transparent transactions.",
                "The stock market can be volatile and unpredictable.",
                "Renewable energy sources are essential for a sustainable future.",
                "The human brain is a complex and fascinating organ.",
                "Space exploration has led to numerous scientific discoveries."
        );
        List<Document> documents = sentences.stream().map(Document::new).collect(Collectors.toList());
        vectorStore.add(documents);
    }
}
