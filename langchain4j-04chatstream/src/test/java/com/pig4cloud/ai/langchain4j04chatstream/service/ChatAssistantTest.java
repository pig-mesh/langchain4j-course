package com.pig4cloud.ai.langchain4j04chatstream.service;

import dev.langchain4j.service.TokenStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatAssistantTest {

    @Autowired
    private ChatAssistant chatAssistant;

    @Test
    void chatStream() {

        TokenStream tokenStream = chatAssistant.chatStream("北京有什么好吃的？");

        tokenStream.onPartialResponse(System.out::println)
                .onCompleteResponse(System.out::println)
                .onError(Throwable::printStackTrace)
                .start();

        // 等待一段时间以确保流式响应完成
        try {
            Thread.sleep(5000); // 等待5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}