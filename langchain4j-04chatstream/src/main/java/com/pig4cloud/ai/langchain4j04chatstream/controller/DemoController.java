package com.pig4cloud.ai.langchain4j04chatstream.controller;

import com.pig4cloud.ai.langchain4j04chatstream.service.ChatAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author lengleng
 * @date 2024/10/7
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/demo")
public class DemoController {

    private final ChatAssistant chatAssistant;

    @GetMapping("/chat")
    public Flux<String> chat(@RequestParam("message") String message) {
        return chatAssistant.chat(message);
    }
}
