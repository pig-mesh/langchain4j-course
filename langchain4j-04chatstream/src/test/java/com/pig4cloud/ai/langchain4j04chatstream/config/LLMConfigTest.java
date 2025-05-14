package com.pig4cloud.ai.langchain4j04chatstream.config;

import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    private StreamingChatModel streamingChatLanguageModel;

    @Test
    void streamingChatLanguageModel() throws InterruptedException {

        streamingChatLanguageModel.chat("hello, 北京有什么好吃的？", new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String s) {

            }

            @Override
            public void onCompleteResponse(ChatResponse chatResponse) {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });

    }
}
