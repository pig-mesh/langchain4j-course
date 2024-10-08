package com.pig4cloud.ai.langchain4j09function.config;

import com.pig4cloud.ai.langchain4j09function.service.FunctionAssistant;
import dev.langchain4j.agent.tool.ToolSpecification;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolExecutor;
import dev.langchain4j.service.tool.ToolProvider;
import dev.langchain4j.service.tool.ToolProviderRequest;
import dev.langchain4j.service.tool.ToolProviderResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static dev.langchain4j.agent.tool.JsonSchemaProperty.description;
import static dev.langchain4j.agent.tool.JsonSchemaProperty.type;

/**
 * @author lengleng
 * @date 2024/10/7
 */
@Configuration(proxyBeanMethods = false)
public class LLMConfig {

    @Bean
    public ChatLanguageModel chatLanguageModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DASHSCOPE_KEY"))
                .modelName("qwen-turbo")  // 设置使用的模型名称
                .logRequests(true)
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public FunctionAssistant functionAssistant(ChatLanguageModel chatLanguageModel) {
        // 工具说明 ToolSpecification
        ToolSpecification toolSpecification = ToolSpecification.builder()
                .name("invoice_assistant")
                .description("根据用户提交的开票信息，开具发票")
                .addParameter("companyName", type("string"), description("公司名称"))
                .addParameter("dutyNumber", type("string"), description("税号"))
                .addParameter("amount", type("string"), description("金额，保留两位有效数字"))
                .build();

        // 业务逻辑 ToolExecutor
        ToolExecutor toolExecutor = (toolExecutionRequest, memoryId) -> {
            String arguments1 = toolExecutionRequest.arguments();
            System.out.println("arguments1 =>>>> " + arguments1);
            return "开具成功";
        };

        return AiServices.builder(FunctionAssistant.class)
                .chatLanguageModel(chatLanguageModel)
                //.tools(Map.of(toolSpecification, toolExecutor))
                //.tools(new InvoiceHandler())
                .toolProvider(new ToolProvider() {
                    @Override
                    public ToolProviderResult provideTools(ToolProviderRequest request) {
                        UserMessage userMessage = request.userMessage();
                        // 含有发票关键字 ，
                        return null;
                    }
                })
                .build();
    }

}
