package com.pig4cloud.ai.langchain4j04chatstream.config;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    private StreamingChatLanguageModel streamingChatLanguageModel;

    @Test
    void streamingChatLanguageModel() throws InterruptedException {

        streamingChatLanguageModel.generate("hello, 北京有什么好吃的？", new StreamingResponseHandler<AiMessage>() {
            @Override
            public void onNext(String token) {
                System.out.println(token);
            }

            @Override
            public void onError(Throwable error) {

            }
        });

        Thread.sleep(20000);
    }
}
