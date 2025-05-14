package com.pig4cloud.ai.langchain4j04chatstream.service;

import dev.langchain4j.service.TokenStream;
import reactor.core.publisher.Flux;

/**
 * @author lengleng
 * @date 2024/10/7
 */
public interface ChatAssistant {

    /**
     * 聊天
     *
     * @param message 消息
     * @return {@link Flux }<{@link String }>
     */
    Flux<String> chat(String message);

    /**
     * 创建TokenStream对象
     *
     * @param message 需要分词的消息内容
     * @return 对应消息内容的TokenStream对象
     */
    TokenStream chatStream(String message);
}
