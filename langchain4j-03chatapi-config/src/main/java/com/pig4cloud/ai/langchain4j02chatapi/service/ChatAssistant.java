package com.pig4cloud.ai.langchain4j02chatapi.service;

/**
 * 聊天助手
 *
 * @author lengleng
 * @date 2024/10/7
 */
public interface ChatAssistant {

    /**
     * 聊天
     *
     * @param message 消息
     * @return {@link String }
     */
    String chat(String message);
}
