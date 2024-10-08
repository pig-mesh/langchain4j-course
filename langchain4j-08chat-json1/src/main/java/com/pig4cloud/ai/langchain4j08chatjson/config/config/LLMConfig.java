package com.pig4cloud.ai.langchain4j08chatjson.config.config;

import com.pig4cloud.ai.langchain4j08chatjson.config.service.NumberExtractor;
import com.pig4cloud.ai.langchain4j08chatjson.config.service.PersonExtractor;
import com.pig4cloud.ai.langchain4j08chatjson.config.service.SentimentAnalyzer;
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
                .modelName("qwen-turbo")  // 设置使用的模型名称
                .logRequests(true)
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public NumberExtractor numberExtractor(ChatLanguageModel chatLanguageModel) {
        return AiServices.create(NumberExtractor.class, chatLanguageModel);
    }

    @Bean
    public SentimentAnalyzer sentimentAnalyzer(ChatLanguageModel chatLanguageModel) {
        return AiServices.create(SentimentAnalyzer.class, chatLanguageModel);
    }

    @Bean
    public PersonExtractor personExtractor(ChatLanguageModel chatLanguageModel) {
        return AiServices.create(PersonExtractor.class, chatLanguageModel);
    }
}
