package com.pig4cloud.ai.langchain4j07chatprompt.config.service;

import dev.langchain4j.model.input.structured.StructuredPrompt;
import lombok.Data;

/**
 * @author lengleng
 * @date 2024/10/8
 */
@Data
@StructuredPrompt("根据中国{{legal}}法律，解答以下问题：{{question}}")
public class LegalPrompt {
    private String legal;
    private String question;
}
