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

    /**
     * 测试配置化ChatModel的基本响应
     * 验证通过配置文件自动装配的ChatModel是否能正常响应
     */
    @Test
    void testConfiguredChatModelBasicResponse() {
        String generate = chatModel.chat("你好");

        System.out.println(generate);
    }

    /**
     * 测试配置化ChatModel的UserMessage处理
     * 验证通过配置文件自动装配的ChatModel是否能正确处理UserMessage对象
     */
    @Test
    void testConfiguredChatModelWithUserMessage(){
        UserMessage userMessage = UserMessage.from("你好");
        ChatResponse response = chatModel.chat(userMessage);

        System.out.println(response.aiMessage().text());
    }

}
