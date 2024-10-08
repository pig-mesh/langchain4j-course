package com.pig4cloud.ai.langchain4j12graalvm.config;

import com.pig4cloud.ai.langchain4j12graalvm.PersonalityTrait;
import dev.langchain4j.classification.EmbeddingModelTextClassifier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LLMConfigTest {

    @Autowired
    private EmbeddingModelTextClassifier<PersonalityTrait> textClassifier;

    @Test
    void textClassifier() {
        List<PersonalityTrait> personalityTraitList= textClassifier.classify("赠人玫瑰，手有余香");

        System.out.println(personalityTraitList);
    }
}
