package com.pig4cloud.ai.langchain4j08chatjson.config.config;

import com.pig4cloud.ai.langchain4j08chatjson.config.service.NumberExtractor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    private NumberExtractor numberExtractor;

    @Test
    void numberExtractor() {
        int days = numberExtractor.extractInt("我今天要请五天假");

        System.out.println(days);
    }
}
