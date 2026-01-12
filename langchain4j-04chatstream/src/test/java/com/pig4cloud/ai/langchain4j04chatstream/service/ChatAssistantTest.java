package com.pig4cloud.ai.langchain4j04chatstream.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.tool.ToolExecution;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ChatAssistantTest {

    @Autowired
    private ChatAssistant chatAssistant;

    /**
     * 基础流式响应测试
     * 演示 TokenStream 的基本用法：onPartialResponse, onCompleteResponse, onError
     */
    @Test
    void testBasicStreaming() throws Exception {
        TokenStream tokenStream = chatAssistant.chatStream("北京有什么好吃的？");

        CompletableFuture<Void> future = new CompletableFuture<>();

        tokenStream
                // 每次收到部分响应时调用（通常是单个 token）
                .onPartialResponse(token -> System.out.print(token))
                // 完整响应生成完毕时调用
                .onCompleteResponse(response -> {
                    System.out.println("\n\n=== 完整响应 ===");
                    System.out.println("内容: " + response.aiMessage().text());
                    System.out.println("Token 使用: " + response.tokenUsage());
                    System.out.println("完成原因: " + response.finishReason());
                    future.complete(null);
                })
                // 发生错误时调用
                .onError(error -> {
                    error.printStackTrace();
                    future.completeExceptionally(error);
                })
                // 启动流式处理（必须调用）
                .start();

        future.get(30, TimeUnit.SECONDS);
    }

    /**
     * 忽略错误测试
     * 使用 ignoreErrors() 忽略流式处理中的错误（错误会以 WARN 级别记录日志）
     */
    @Test
    void testIgnoreErrors() throws Exception {
        TokenStream tokenStream = chatAssistant.chatStream("你好");

        CompletableFuture<Void> future = new CompletableFuture<>();

        tokenStream
                .onPartialResponse(System.out::print)
                // 忽略所有错误，错误会以 WARN 级别记录
                .ignoreErrors()
                .onCompleteResponse(response -> {
                    System.out.println("\n完成");
                    future.complete(null);
                })
                .start();

        future.get(30, TimeUnit.SECONDS);
    }

    /**
     * RAG 检索内容回调测试
     * 当配置了 ContentRetriever 时，onRetrieved 会在检索到内容后被调用
     */
    @Test
    void testOnRetrieved() throws Exception {
        TokenStream tokenStream = chatAssistant.chatStream("什么是 LangChain4j？");

        CompletableFuture<Void> future = new CompletableFuture<>();

        tokenStream
                // 当从向量存储检索到内容时调用（需要配置 ContentRetriever）
                .onRetrieved((List<Content> contents) -> {
                    System.out.println("=== 检索到的内容 ===");
                    contents.forEach(content -> {
                        System.out.println("来源: " + content.textSegment().metadata());
                        System.out.println("内容: " + content.textSegment().text());
                        System.out.println("---");
                    });
                })
                .onPartialResponse(System.out::print)
                .onCompleteResponse(response -> {
                    System.out.println("\n完成");
                    future.complete(null);
                })
                .onError(error -> {
                    error.printStackTrace();
                    future.completeExceptionally(error);
                })
                .start();

        future.get(30, TimeUnit.SECONDS);
    }

    /**
     * 工具执行回调测试
     * beforeToolExecution: 工具执行前调用
     * onToolExecuted: 工具执行后调用
     */
    @Test
    void testToolExecutionCallbacks() throws Exception {
        TokenStream tokenStream = chatAssistant.chatStream("今天天气怎么样？");

        CompletableFuture<Void> future = new CompletableFuture<>();

        tokenStream
                // 工具执行前调用，包含 ToolExecutionRequest（工具名称、参数等）
                .beforeToolExecution(beforeToolExecution -> {
                    System.out.println("=== 即将执行工具 ===");
                    System.out.println("工具请求: " + beforeToolExecution.request());
                })
                // 工具执行后调用，包含 ToolExecutionRequest 和执行结果
                .onToolExecuted((ToolExecution toolExecution) -> {
                    System.out.println("=== 工具执行完成 ===");
                    System.out.println("工具请求: " + toolExecution.request());
                    System.out.println("执行结果: " + toolExecution.result());
                })
                .onPartialResponse(System.out::print)
                .onCompleteResponse(response -> {
                    System.out.println("\n完成");
                    future.complete(null);
                })
                .onError(error -> {
                    error.printStackTrace();
                    future.completeExceptionally(error);
                })
                .start();

        future.get(60, TimeUnit.SECONDS);
    }

    /**
     * 中间响应回调测试
     * 当使用工具时，onIntermediateResponse 会在每次工具调用后、最终响应前被调用
     */
    @Test
    void testIntermediateResponse() throws Exception {
        TokenStream tokenStream = chatAssistant.chatStream("计算 15 + 27 等于多少？");

        CompletableFuture<Void> future = new CompletableFuture<>();

        tokenStream
                // 中间响应回调（工具调用场景下，最终响应前的中间结果）
                .onIntermediateResponse((ChatResponse intermediateResponse) -> {
                    System.out.println("=== 中间响应 ===");
                    AiMessage aiMessage = intermediateResponse.aiMessage();
                    if (aiMessage.hasToolExecutionRequests()) {
                        System.out.println("工具调用请求: " + aiMessage.toolExecutionRequests());
                    }
                    if (aiMessage.text() != null) {
                        System.out.println("中间文本: " + aiMessage.text());
                    }
                })
                .onPartialResponse(System.out::print)
                .onCompleteResponse(response -> {
                    System.out.println("\n=== 最终响应 ===");
                    System.out.println(response.aiMessage().text());
                    future.complete(null);
                })
                .onError(error -> {
                    error.printStackTrace();
                    future.completeExceptionally(error);
                })
                .start();

        future.get(60, TimeUnit.SECONDS);
    }

    /**
     * 完整 API 链式调用示例
     * 展示 TokenStream 所有可用的回调方法
     */
    @Test
    void testFullApiChain() throws Exception {
        TokenStream tokenStream = chatAssistant.chatStream("介绍一下 Java 25 的新特性");

        CompletableFuture<Void> future = new CompletableFuture<>();
        StringBuilder fullResponse = new StringBuilder();

        tokenStream
                // 1. RAG 检索回调（需要配置 ContentRetriever）
                .onRetrieved(contents -> {
                    System.out.println("[检索] 找到 " + contents.size() + " 条相关内容");
                })
                // 2. 工具执行前回调
                .beforeToolExecution(before -> {
                    System.out.println("[工具] 准备执行: " + before.request().name());
                })
                // 3. 工具执行后回调
                .onToolExecuted(execution -> {
                    System.out.println("[工具] 执行完成: " + execution.request().name());
                })
                // 4. 中间响应回调
                .onIntermediateResponse(intermediate -> {
                    System.out.println("[中间响应] 收到中间结果");
                })
                // 5. 部分响应回调（流式 token）
                .onPartialResponse(token -> {
                    System.out.print(token);
                    fullResponse.append(token);
                })
                // 6. 完整响应回调
                .onCompleteResponse(response -> {
                    System.out.println("\n\n[完成] Token 使用统计:");
                    if (response.tokenUsage() != null) {
                        System.out.println("  - 输入 tokens: " + response.tokenUsage().inputTokenCount());
                        System.out.println("  - 输出 tokens: " + response.tokenUsage().outputTokenCount());
                        System.out.println("  - 总计 tokens: " + response.tokenUsage().totalTokenCount());
                    }
                    future.complete(null);
                })
                // 7. 错误回调
                .onError(error -> {
                    System.err.println("[错误] " + error.getMessage());
                    future.completeExceptionally(error);
                })
                // 8. 启动流式处理
                .start();

        future.get(60, TimeUnit.SECONDS);
        System.out.println("\n完整响应长度: " + fullResponse.length() + " 字符");
    }
}
