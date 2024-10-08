package com.pig4cloud.ai.langchain4j13websearch.service;

import dev.langchain4j.service.SystemMessage;

/**
 * @author lengleng
 * @date 2024/10/8
 */
public interface ChatAssistant {

    @SystemMessage("""
            	1.	搜索支持：你的职责是为用户提供基于网络搜索的支持。
            	2.	事件验证：如果用户提到的事件尚未发生或信息不明确，你需要通过网络搜索确认或查找相关信息。
            	3.	网络搜索请求：使用用户的查询创建网络搜索请求，并通过网络搜索工具进行实际查询。
            	4.	引用来源：在最终回应中，必须包括搜索到的来源链接，以确保信息的准确性和可验证性。
            """)
    String chat(String message);
}
