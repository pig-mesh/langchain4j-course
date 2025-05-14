package com.pig4cloud.ai.langchain4j12graalvm.config;

import dev.langchain4j.code.CodeExecutionEngine;
import dev.langchain4j.code.graalvm.GraalVmJavaScriptExecutionEngine;
import org.junit.jupiter.api.Test;

/**
 * @author lengleng
 * @date 2024/10/8
 */
public class GraalVMTests {

    /**
     * 测试GraalVM的JavaScript代码执行功能
     * 验证系统能否通过GraalVM引擎正确执行斐波那契函数并返回结果
     */
    @Test
    public void testJavaScriptCodeExecution() {

        CodeExecutionEngine engine = new GraalVmJavaScriptExecutionEngine();

        String code = """
        function fibonacci(n) {
            if (n <= 1) return n;
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
                        
        fibonacci(10)
        """;

        String result = engine.execute(code);
        System.out.println(result);
    }
}
