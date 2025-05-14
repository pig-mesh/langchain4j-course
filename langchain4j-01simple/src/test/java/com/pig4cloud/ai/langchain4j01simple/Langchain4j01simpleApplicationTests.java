package com.pig4cloud.ai.langchain4j01simple;

import dev.langchain4j.model.chat.ChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j01simpleApplicationTests {
    @Autowired
    private ChatModel chatModel;


    /**
     * 测试ChatModel基本功能
     * 验证ChatModel是否能正确响应简单的问候语"你好"
     */
    @Test
    void testBasicChatModelResponse() {
        String chatted = chatModel.chat("你好");
        System.out.println(chatted);
    }

}
