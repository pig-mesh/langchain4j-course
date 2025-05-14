package com.pig4cloud.ai.langchain4j02chatapi;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Langchain4j02chatapiApplicationTests {

    @Autowired
    private ChatModel chatModel;

    @Test
    void contextLoads() {
        //你好！很高兴能为你服务。有什么问题或需要帮助的吗？
        //你好！很高兴能为你提供帮助。有什么我可以为你做的吗？
        String generate = chatModel.chat("你好");
        System.out.println(generate);
    }

    @Test
    void testChatMessage() {
        UserMessage userMessage = UserMessage.from("你好");
        ChatResponse chatResponse = chatModel.chat(userMessage);
        System.out.println(chatResponse.aiMessage().text());
    }

}
