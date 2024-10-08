package com.pig4cloud.ai.langchain4j02chatapi;

import com.pig4cloud.ai.langchain4j02chatapi.service.ChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatAssistantTest {

    @Autowired
    private ChatAssistant chatAssistant;

    @Test
    void chat() {
        String chat = chatAssistant.chat("你好，我的名字叫冷冷");
        System.out.println(chat);
        String chat2 = chatAssistant.chat("我的名字是什么？");
        System.out.println(chat2);
    }
}
