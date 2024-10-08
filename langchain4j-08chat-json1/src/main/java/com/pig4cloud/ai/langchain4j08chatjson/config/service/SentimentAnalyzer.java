package com.pig4cloud.ai.langchain4j08chatjson.config.service;

/**
 * @author lengleng
 * @date 2024/10/8
 */

import dev.langchain4j.service.UserMessage;

public interface SentimentAnalyzer {

    // text=假期结束开始上班
    @UserMessage("{{it}} 是否具有正面情感？")
    boolean isPositive(String text);

    // text=假期结束开始上班
    @UserMessage("分析 {{it}} 的情感")
    Sentiment analyzeSentimentOf(String text);

    enum Sentiment {
        POSITIVE, // 正面情感
        NEGATIVE, // 负面情感
        NEUTRAL   // 中立情感
    }
}

