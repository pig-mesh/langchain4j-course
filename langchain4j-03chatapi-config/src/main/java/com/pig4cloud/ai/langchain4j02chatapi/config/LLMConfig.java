package com.pig4cloud.ai.langchain4j02chatapi.config;

import com.pig4cloud.ai.langchain4j02chatapi.service.ChatAssistant;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

/**
 * 大模型配置
 *
 * @author lengleng
 * @date 2024/10/7
 */
@Configuration(proxyBeanMethods = false)
public class LLMConfig {

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_KEY"))
                .modelName("qwen-turbo")
                .logRequests(true)
                .logResponses(true)
                .maxRetries(1)
                .timeout(Duration.ofSeconds(10))
                .listeners(List.of(new TestChatModelListener()))
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant(ChatLanguageModel chatLanguageModel) {
        return AiServices.create(ChatAssistant.class, chatLanguageModel);
    }

}
