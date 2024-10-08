package com.pig4cloud.ai.langchain4j09function.service;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lengleng
 * @date 2024/10/8
 */
@Slf4j
public class InvoiceHandler {
    @Tool("根据用户提交的开票信息进行开票")
    public String handle(@P("公司名称") String companyName, String dutyNumber,@P("金额保留两位有效数字") String amount) {
        log.info("companyName =>>>> {} dutyNumber =>>>> {} amount =>>>> {}", companyName, dutyNumber, amount);
        return "开票成功";
    }
}

