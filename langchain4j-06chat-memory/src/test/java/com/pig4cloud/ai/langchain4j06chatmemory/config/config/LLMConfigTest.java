package com.pig4cloud.ai.langchain4j06chatmemory.config.config;

import com.pig4cloud.ai.langchain4j06chatmemory.config.service.AiAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class LLMConfigTest {

    @Autowired
    private AiAssistant aiAssistant;

    @Test
    void aiAssistant() {

        String chat = aiAssistant.chat("你好 我的名字是冷冷");

        System.out.println(chat);


        chat = aiAssistant.chat("你好 我的名字是什么？");

        System.out.println(chat);
    }

    @Test
    void  chat2(){
        aiAssistant.chat(1L, "你好！我的名字是冷冷1.");
        aiAssistant.chat(2L, "你好！我的名字是冷冷2.");

        String chat = aiAssistant.chat(1L, "我的名字是什么");
        System.out.println(chat);
        chat = aiAssistant.chat(2L, "我的名字是什么");
        System.out.println(chat);
    }
}
