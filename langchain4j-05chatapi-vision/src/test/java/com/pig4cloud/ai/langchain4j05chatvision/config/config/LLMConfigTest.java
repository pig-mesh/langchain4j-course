package com.pig4cloud.ai.langchain4j05chatvision.config.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    private ChatLanguageModel chatLanguageModel;


    @Test
    void chatLanguageModel() throws IOException {


        String generate = chatLanguageModel.generate("你好 我是 冷冷");

        System.out.println(generate);

        generate = chatLanguageModel.generate("你好 我的名字是什么？");
        System.out.println(generate);

    }
}
