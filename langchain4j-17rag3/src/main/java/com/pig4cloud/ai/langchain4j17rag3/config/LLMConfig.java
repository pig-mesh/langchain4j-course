package com.pig4cloud.ai.langchain4j17rag3.config;

import com.pig4cloud.ai.langchain4j17rag3.service.ChatAssistant;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.jina.JinaScoringModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.scoring.ScoringModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.content.aggregator.ReRankingContentAggregator;
import dev.langchain4j.rag.content.injector.DefaultContentInjector;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.rag.query.transformer.CompressingQueryTransformer;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lengleng
 * @date 2024/10/9
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

    /**
     * 嵌入存储 (简易内存存储)
     *
     * @return {@link InMemoryEmbeddingStore }<{@link TextSegment }>
     */
    @Bean
    public InMemoryEmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }


    @Bean
    public ChatAssistant assistant(ChatLanguageModel chatLanguageModel, EmbeddingStore<TextSegment> embeddingStore) {
        ScoringModel scoringModel = JinaScoringModel.builder()
                .apiKey(System.getenv("JINA_API_KEY"))
                .modelName("jina-reranker-v2-base-multilingual")
                .build();

        DefaultRetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .queryTransformer(new CompressingQueryTransformer(chatLanguageModel)) //  调用大模型，格式化用户提问
                .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore)) // 从嵌入存储中检索内容
                .queryRouter(new DefaultQueryRouter(EmbeddingStoreContentRetriever.from(embeddingStore)))// 多个内容源
                .contentInjector(new DefaultContentInjector()) // 注入内容
                .contentAggregator(new ReRankingContentAggregator(scoringModel)) // 聚合内容
                .build();

        return AiServices.builder(ChatAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .retrievalAugmentor(retrievalAugmentor)
                //.contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore))
                .build();
    }
}
