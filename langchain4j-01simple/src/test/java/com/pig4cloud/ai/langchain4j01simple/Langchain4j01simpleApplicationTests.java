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
     * 通过调用chatModel的chat方法并输出结果来验证。
     */
    @Test
    void contextLoads() {
        String chatted = chatModel.chat("你好");
        System.out.println(chatted);
    }

}
