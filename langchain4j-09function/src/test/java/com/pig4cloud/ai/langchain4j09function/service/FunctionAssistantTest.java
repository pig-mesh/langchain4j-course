package com.pig4cloud.ai.langchain4j09function.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FunctionAssistantTest {

    @Autowired
    FunctionAssistant functionAssistant;

    @Test
    void chat() {

        String chat = functionAssistant.chat("帮我开具一张发票， 公司：华为科技有限公司 税号：ZXC123 金额： 1000.12");

        System.out.println(chat);
    }
}
