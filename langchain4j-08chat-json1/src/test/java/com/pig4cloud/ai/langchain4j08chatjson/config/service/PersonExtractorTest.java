package com.pig4cloud.ai.langchain4j08chatjson.config.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonExtractorTest {

    @Autowired
    private PersonExtractor personExtractor;

    /**
     * 测试JSON结构化信息提取功能
     * 验证系统能否从自然语言描述中提取出结构化的用户信息
     */
    @Test
    void testPersonDataExtractionFromText() {

        PersonExtractor.Person person = personExtractor.extractPerson("帮我创建一个用户，名字叫张三，生日是1990-01-01");

        System.out.println(person);
    }
}
