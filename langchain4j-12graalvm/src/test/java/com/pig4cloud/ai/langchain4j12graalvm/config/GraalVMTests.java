package com.pig4cloud.ai.langchain4j12graalvm.config;

import dev.langchain4j.code.CodeExecutionEngine;
import dev.langchain4j.code.graalvm.GraalVmJavaScriptExecutionEngine;
import org.junit.jupiter.api.Test;

/**
 * @author lengleng
 * @date 2024/10/8
 */
public class GraalVMTests {

    @Test
    public void test() {

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
