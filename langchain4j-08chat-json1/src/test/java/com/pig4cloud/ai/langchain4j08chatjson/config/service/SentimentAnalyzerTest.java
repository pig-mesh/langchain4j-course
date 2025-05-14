package com.pig4cloud.ai.langchain4j08chatjson.config.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SentimentAnalyzerTest {

    @Autowired
    private SentimentAnalyzer sentimentAnalyzer;

    @Test
    void isPositive() {
        boolean positive = sentimentAnalyzer.isPositive("假期结束开始上班");
        System.out.println(positive);
    }

    @Test
    void analyzeSentimentOf() {
        SentimentAnalyzer.Sentiment sentiment = sentimentAnalyzer.analyzeSentimentOf("假期结束开始上班");

        System.out.println(sentiment);
    }
}
