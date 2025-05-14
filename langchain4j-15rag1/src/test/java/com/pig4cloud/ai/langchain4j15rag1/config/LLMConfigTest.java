package com.pig4cloud.ai.langchain4j15rag1.config;

import com.pig4cloud.ai.langchain4j15rag1.service.ChatAssistant;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.source.ClassPathSource;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LLMConfigTest {


    @Autowired
    InMemoryEmbeddingStore<TextSegment> embeddingStore;

    @Autowired
    ChatAssistant chatAssistant;


    /**
     * 测试基于检索增强生成(RAG)的文档问答功能
     * 验证系统能否加载文档、建立向量存储并正确回答基于文档内容的问题
     */
    @Test
    void testDocumentRetrievalAndQuestionAnswering() {
        Document document = DocumentLoader.load(ClassPathSource.from("AI.txt"), new TextDocumentParser());
        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        String chat = chatAssistant.chat("合同的金额是多少");
        System.out.println(chat);
    }
}
