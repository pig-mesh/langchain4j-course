package com.pig4cloud.ai.langchain4j04chatstream.service;

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
}
