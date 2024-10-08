package com.pig4cloud.ai.langchain4j08chatjson.config.service;

import dev.langchain4j.model.output.structured.Description;
import dev.langchain4j.service.UserMessage;
import lombok.Data;

import java.time.LocalDate;

public interface PersonExtractor {

    @UserMessage("Extract information about a person from {{it}}")
    Person extractPerson(String text);

    @Data
    class Person {
        @Description("name of a person") // 增加字段描述，让大模型更理解字段含义
        private String name;
        private LocalDate birthDate;
    }
}

