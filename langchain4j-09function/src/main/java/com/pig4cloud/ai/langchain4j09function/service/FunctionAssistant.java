package com.pig4cloud.ai.langchain4j09function.service;

/**
 * @author lengleng
 * @date 2024/10/8
 */
public interface FunctionAssistant {

    //  用户提问： 帮我开具发票，开票信息是 ：公司名称xxx 税号 xx 金额
    String chat(String message);
}
