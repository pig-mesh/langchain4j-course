package com.pig4cloud.ai.langchain4j08chatjson.config.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SentimentAnalyzerTest {

    @Autowired
    private SentimentAnalyzer sentimentAnalyzer;

    /**
     * 测试情感分析的简单判断功能
     * 验证系统能否正确判断文本"假期结束开始上班"的情感倾向是否为积极
     */
    @Test
    void testSimpleSentimentPositiveCheck() {
        boolean positive = sentimentAnalyzer.isPositive("假期结束开始上班");
        System.out.println(positive);
    }

    /**
     * 测试情感分析的详细分析功能
     * 验证系统能否对文本"假期结束开始上班"进行完整的情感分析并返回结构化结果
     */
    @Test
    void testDetailedSentimentAnalysis() {
        SentimentAnalyzer.Sentiment sentiment = sentimentAnalyzer.analyzeSentimentOf("假期结束开始上班");

        System.out.println(sentiment);
    }
}
