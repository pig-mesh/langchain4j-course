package com.pig4cloud.ai.langchain4j10embedding.config;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    EmbeddingModel embeddingModel;

    @Autowired
    private QdrantClient qdrantClient;

    @Autowired
    private EmbeddingStore<TextSegment> embeddingStore;

    /**
     * 测试Qdrant向量数据库集合创建
     * 验证系统能否正确创建具有指定维度和相似度度量的向量集合
     */
    @Test
    void testCreateQdrantCollection() {
        var vectorParams = Collections.VectorParams.newBuilder()
                .setDistance(Collections.Distance.Cosine)
                .setSize(1024)
                .build();
        qdrantClient.createCollectionAsync("testv", vectorParams);
    }

    /**
     * 测试文本向量化基本功能
     * 验证嵌入模型能否将文本转换为向量表示
     */
    @Test
    void testTextToEmbeddingConversion() {
        Response<Embedding> embeddingResponse = embeddingModel.embed("测试文本，文本向量化");
        System.out.println(embeddingResponse);
    }


    /**
     * 测试文本片段存储功能
     * 验证系统能否将带有元数据的文本片段向量化并保存到嵌入存储中
     */
    @Test
    void testTextSegmentStorageWithMetadata() {
        TextSegment segment1 = TextSegment.from("浏览器报错 404，请检测您输入的路径是否正确");
        segment1.metadata().put("author", "冷冷");
        Embedding embedding1 = embeddingModel.embed(segment1).content();
        embeddingStore.add(embedding1, segment1);
    }

    /**
     * 测试基本向量搜索功能
     * 验证系统能否通过语义相似度查询找到最相关的文本片段
     */
    @Test
    void testBasicVectorSimilaritySearch() {
        Embedding queryEmbedding = embeddingModel.embed("404 是哪里的问题？").content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);
        System.out.println(searchResult.matches().get(0).embedded().text());
    }

    /**
     * 测试带元数据过滤的向量搜索功能
     * 验证系统能否在语义搜索的同时应用元数据过滤条件
     */
    @Test
    void testVectorSearchWithMetadataFiltering() {
        Embedding queryEmbedding = embeddingModel.embed("404 是哪里的问题？").content();
        EmbeddingSearchRequest embeddingSearchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .filter(metadataKey("author").isEqualTo("冷冷"))
                .maxResults(1)
                .build();

        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(embeddingSearchRequest);

        System.out.println(searchResult);
    }
}
