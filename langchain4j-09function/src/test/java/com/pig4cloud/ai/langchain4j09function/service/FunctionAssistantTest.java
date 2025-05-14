package com.pig4cloud.ai.langchain4j09function.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FunctionAssistantTest {

    @Autowired
    FunctionAssistant functionAssistant;

    /**
     * 测试函数调用助手的开票功能
     * 验证系统能否正确解析用户开票请求并生成适当的响应
     */
    @Test
    void testInvoiceGenerationFunction() {

        String chat = functionAssistant.chat("帮我开具一张发票， 公司：华为科技有限公司 税号：ZXC123 金额： 1000.12");

        System.out.println(chat);
    }
}
