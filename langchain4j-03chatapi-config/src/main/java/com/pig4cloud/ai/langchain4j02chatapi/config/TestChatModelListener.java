package com.pig4cloud.ai.langchain4j02chatapi.config;

import dev.langchain4j.model.chat.listener.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试大模型调用监听器
 *
 * @author lengleng
 * @date 2024/10/7
 */
@Slf4j
public class TestChatModelListener implements ChatModelListener {
    /**
     * This method is called before the request is sent to the model.
     *
     * @param requestContext The request context. It contains the {@link ChatModelRequest} and attributes.
     *                       The attributes can be used to pass data between methods of this listener
     *                       or between multiple listeners.
     */
    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        requestContext.attributes().put("test", "test");
        log.info("请求参数:{}", requestContext);
    }

    /**
     * This method is called after the response is received from the model.
     *
     * @param responseContext The response context.
     *                        It contains {@link ChatModelResponse}, corresponding {@link ChatModelRequest} and attributes.
     *                        The attributes can be used to pass data between methods of this listener
     *                        or between multiple listeners.
     */
    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        Object object = responseContext.attributes().get("test");

        log.info("返回结果:{}", object);
    }

    /**
     * This method is called when an error occurs during interaction with the model.
     *
     * @param errorContext The error context.
     *                     It contains the error, corresponding {@link ChatModelRequest},
     *                     partial {@link ChatModelResponse} (if available) and attributes.
     *                     The attributes can be used to pass data between methods of this listener
     *                     or between multiple listeners.
     */
    @Override
    public void onError(ChatModelErrorContext errorContext) {
        log.error("请求异常:{}", errorContext);
    }
}
