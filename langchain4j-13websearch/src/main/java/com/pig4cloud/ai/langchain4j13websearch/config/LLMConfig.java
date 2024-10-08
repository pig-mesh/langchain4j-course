package com.pig4cloud.ai.langchain4j13websearch.config;

import com.pig4cloud.ai.langchain4j13websearch.service.ChatAssistant;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.web.search.WebSearchTool;
import dev.langchain4j.web.search.searchapi.SearchApiWebSearchEngine;
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
                .modelName("qwen-turbo")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }


    @Bean
    public ChatAssistant chatAssistant(ChatLanguageModel chatLanguageModel) {
        SearchApiWebSearchEngine searchEngine = SearchApiWebSearchEngine.builder()
                .apiKey("p8SZVNAweqTtoZBBTVnXttcj")// 测试使用
                .engine("google")
                .build();

        return AiServices.builder(ChatAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .tools(new WebSearchTool(searchEngine))
                .build();
    }
}
