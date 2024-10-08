package com.pig4cloud.ai.langchain4j06chatmemory.config.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

/**
 * @author lengleng
 * @date 2024/10/8
 */
public interface AiAssistant {

    String chat(String message);

    /**
     * 聊天
     *
     * @param userId  用户 ID
     * @param message 消息
     * @return {@link String }
     */
    String chat(@MemoryId Long userId, @UserMessage String message);
}
