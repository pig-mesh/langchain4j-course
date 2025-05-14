package com.pig4cloud.ai.langchain4j07chatprompt.config.service;

import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class LegalAssistantTest {

    @Autowired
    private LegalAssistant legalAssistant;

    /**
     * 测试法律助手的基本问答功能
     * 验证LegalAssistant能否正确回答关于著作权的简单法律问题
     */
    @Test
    void testLegalAssistantBasicQuery() {
        String chat = legalAssistant.chat("什么是著作权？");

        System.out.println(chat);
    }

    /**
     * 测试使用LegalPrompt对象进行提问
     * 验证LegalAssistant能否处理结构化的法律问题对象
     */
    @Test
    void testLegalAssistantWithPromptObject() {
        LegalPrompt prompt = new LegalPrompt();
        prompt.setLegal("著作权");
        prompt.setQuestion("LGPL协议是什么？");

        String chat = legalAssistant.chat(prompt);

        System.out.println(chat);
    }

    /**
     * 测试不同PromptTemplate模板的使用方法
     * 验证多种方式创建和填充法律问题模板的效果
     */
    @Test
    void testPromptTemplateVariations() {
        // 默认 form 构造使用 it 属性作为默认占位符
        PromptTemplate template = PromptTemplate.from("请解释中国法律中的'{{it}}'概念。");
        Prompt prompt = template.apply("知识产权");
        System.out.println(prompt.text()); // 输出: 请解释中国法律中的'知识产权'概念。

// apply 方法接受 Map 作为参数
        PromptTemplate template2 = PromptTemplate.from("请解释中国法律中的'{{legal1}}'概念。");
        Prompt prompt2 = template2.apply(Map.of("legal1", "知识产权"));
        System.out.println(prompt2.text());
    }
}
