package com.pig4cloud.ai.langchain4j05chatvision.config.config;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Base64;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    private ChatModel chatModel;

    @Value(("1.png"))
    private Resource resource;


    /**
     * 测试多模态图像识别功能
     * 验证ChatModel能否正确分析图片并从中提取上证指数数据
     */
    @Test
    void testImageRecognitionAndDataExtraction() throws IOException {
        byte[] byteArray = resource.getContentAsByteArray();
        String encodeToString = Base64.getEncoder().encodeToString(byteArray);

        UserMessage userMessage = UserMessage.from(TextContent.from("从以下图片中获取 9.30号的上证指数"),
                ImageContent.from(encodeToString, "image/png"));

        ChatResponse response = chatModel.chat(userMessage);
        System.out.println(response.aiMessage().text());

    }
}

