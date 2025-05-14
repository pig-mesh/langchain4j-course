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


    /**
     * 测试流式聊天模型的基本功能
     * 验证StreamingChatModel能否正确处理流式响应，并通过回调接口获取结果
     */
    @Test
    void testStreamingChatModelWithCallback() throws InterruptedException {

        streamingChatLanguageModel.chat("hello, 北京有什么好吃的？", new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String token) {
                System.out.println("onNext: " + token);
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
