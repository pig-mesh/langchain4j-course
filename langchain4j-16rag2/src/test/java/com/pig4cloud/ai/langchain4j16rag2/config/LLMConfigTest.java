package com.pig4cloud.ai.langchain4j16rag2.config;

import com.pig4cloud.ai.langchain4j16rag2.service.ChatAssistant;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentLoader;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.document.source.ClassPathSource;
import dev.langchain4j.data.document.splitter.DocumentByCharacterSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    InMemoryEmbeddingStore<TextSegment> embeddingStore;

    @Autowired
    ChatAssistant chatAssistant;

    /**
     * 测试Word文档格式的RAG系统
     * 验证系统能否正确加载.docx文件，建立向量存储并回答文档相关问题
     */
    @Test
    void testWordDocumentRagQuestionAnswering() {
        Document document = DocumentLoader.load(ClassPathSource.from("AI.docx"), new ApacheTikaDocumentParser());
        EmbeddingStoreIngestor.ingest(document, embeddingStore);
        String chat = chatAssistant.chat("合同的金额是多少");
        System.out.println(chat);
    }

    /**
     * 测试Apache Tika文档解析器功能
     * 验证系统能否使用Tika解析器正确读取.docx文件的内容
     */
    @Test
    void testApacheTikaDocumentParsing() throws IOException {
        InputStream inputStream = ClassPathSource.from("AI.docx").inputStream();
        Document document = new ApacheTikaDocumentParser().parse(inputStream);

        System.out.println(document);
    }

    /**
     * 测试文档分段功能
     * 验证系统能否将文档按字符数量切分成多个文本片段
     */
    @Test
    void testDocumentCharacterSplitting() throws IOException {
        InputStream inputStream = ClassPathSource.from("AI.docx").inputStream();
        Document document = new ApacheTikaDocumentParser().parse(inputStream);

        List<TextSegment> split = new DocumentByCharacterSplitter(100, 0).split(document);

        System.out.println(split);

    }


}
