package com.pig4cloud.ai.langchain4j02chatapi;


import com.pig4cloud.ai.langchain4j02chatapi.service.ChatAssistant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatAssistantTest {

    @Resource
    private ChatAssistant chatAssistant;

    @Test
    void chat() {

        String chat = chatAssistant.chat("hello, 北京有什么好吃的？");

        System.out.println(chat);
    }
}
