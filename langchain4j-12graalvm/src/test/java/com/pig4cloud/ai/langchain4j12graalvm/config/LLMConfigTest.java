package com.pig4cloud.ai.langchain4j12graalvm.config;

import com.pig4cloud.ai.langchain4j12graalvm.service.AiAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    private AiAssistant aiAssistant;

    @Test
    void aiAssistant() {
        String chat = aiAssistant.chat("What is the square root of 485906798473894056 in scientific notation?");
        System.out.println(chat);
    }
}
