package com.pig4cloud.ai.langchain4j13websearch.config;

import com.pig4cloud.ai.langchain4j13websearch.service.ChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    private ChatAssistant chatAssistant;

    @Test
    void chatAssistant() {
        String chat = chatAssistant.chat("今天20241008 上证指数是多少？");

        System.out.println(chat);
    }
}
