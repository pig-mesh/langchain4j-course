package com.pig4cloud.ai.langchain4j06chatmemory.config.config;

import com.pig4cloud.ai.langchain4j06chatmemory.config.service.AiAssistant;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lengleng
 * @date 2024/10/7
 */
@Configuration(proxyBeanMethods = false)
public class LLMConfig {

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_KEY"))
                .modelName("qwen-long")  // 设置使用的模型名称
                .logRequests(true)
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }


    @Bean
    public AiAssistant aiAssistant(ChatLanguageModel chatLanguageModel) {
        return AiServices.builder(AiAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
                .build()
                ;
    }

}
