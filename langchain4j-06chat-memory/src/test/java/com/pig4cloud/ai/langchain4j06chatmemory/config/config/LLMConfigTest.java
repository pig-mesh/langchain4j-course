package com.pig4cloud.ai.langchain4j06chatmemory.config.config;

import com.pig4cloud.ai.langchain4j06chatmemory.config.service.AiAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class LLMConfigTest {

    @Autowired
    private AiAssistant aiAssistant;

    /**
     * 测试AI助手的单一对话记忆功能
     * 验证AI助手是否能在同一会话中记住用户提供的姓名信息
     */
    @Test
    void testSingleConversationMemory() {

        String chat = aiAssistant.chat("你好 我的名字是冷冷");

        System.out.println(chat);


        chat = aiAssistant.chat("你好 我的名字是什么？");

        System.out.println(chat);
    }

    /**
     * 测试AI助手的多用户对话记忆功能
     * 验证AI助手是否能同时为不同用户ID维护独立的对话记忆
     */
    @Test
    void testMultiUserConversationMemory(){
        aiAssistant.chat(1L, "你好！我的名字是冷冷1.");
        aiAssistant.chat(2L, "你好！我的名字是冷冷2.");

        String chat = aiAssistant.chat(1L, "我的名字是什么");
        System.out.println(chat);
        chat = aiAssistant.chat(2L, "我的名字是什么");
        System.out.println(chat);
    }
}
