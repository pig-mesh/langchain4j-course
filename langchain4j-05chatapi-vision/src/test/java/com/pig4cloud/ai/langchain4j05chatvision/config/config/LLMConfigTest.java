package com.pig4cloud.ai.langchain4j05chatvision.config.config;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.output.Response;
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
    private ChatLanguageModel chatLanguageModel;

    @Value(("1.png"))
    private Resource resource;


    @Test
    void chatLanguageModel() throws IOException {
        byte[] byteArray = resource.getContentAsByteArray();
        String encodeToString = Base64.getEncoder().encodeToString(byteArray);

        UserMessage userMessage = UserMessage.from(TextContent.from("从以下图片中获取 9.30号的上证指数"),
                ImageContent.from(encodeToString,"image/png"));

        Response<AiMessage> response = chatLanguageModel.generate(userMessage);
        System.out.println(response.content().text());


//        String generate = chatLanguageModel.generate("你好 我是 冷冷");
//        System.out.println(generate);
//        generate = chatLanguageModel.generate("你好 我的名字是什么？");
//        System.out.println(generate);

    }
}

