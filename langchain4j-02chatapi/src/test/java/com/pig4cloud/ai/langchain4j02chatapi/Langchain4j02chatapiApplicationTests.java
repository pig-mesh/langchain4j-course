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
     * 测试直接使用字符串调用ChatModel
     * 验证使用简单字符串参数调用chat方法的响应
     */
    @Test
    void testChatWithStringInput() {
        //你好！很高兴能为你服务。有什么问题或需要帮助的吗？
        //你好！很高兴能为你提供帮助。有什么我可以为你做的吗？
        String generate = chatModel.chat("你好");
        System.out.println(generate);
    }

    /**
     * 测试使用UserMessage对象调用ChatModel
     * 验证通过创建UserMessage对象并传递给chat方法的响应处理
     */
    @Test
    void testChatWithUserMessageObject() {
        UserMessage userMessage = UserMessage.from("你好");
        ChatResponse chatResponse = chatModel.chat(userMessage);
        System.out.println(chatResponse.aiMessage().text());
    }

}
