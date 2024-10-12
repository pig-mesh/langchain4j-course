package com.pig4cloud.ai.langchain4j14sensitive;

import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.core.SensitiveWordHelper;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import org.junit.jupiter.api.Test;

import java.util.List;

class Langchain4j14SensitiveTest {

    final SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
            // 使用默认的敏感词词库（黑名单）
            .wordDeny(new IWordDeny() {
                @Override
                public List<String> deny() {
                    return List.of("冷冷");
                }
            })
            // 使用默认的白名单词库，白名单中的词不会被视为敏感词，即使它们在黑名单中
            .wordAllow(WordAllows.defaults())
            // 忽略大小写，例如："FuCk" 和 "fuck" 将被同等对待
            .ignoreCase(true)
            // 忽略全角和半角字符的区别，例如："ｆｕｃｋ" 和 "fuck" 将被同等对待
            .ignoreWidth(true)
            // 启用连续数字检测， 可用于检测电话号码、QQ号等
            .enableNumCheck(true)
            // 启用邮箱地址检测，可用于过滤包含邮箱地址的文本
            .enableEmailCheck(true)
            // 初始化敏感词过滤器， 这一步必须在所有配置完成后调用
            .init();

    @Test
    public void test1(){
        String text = "\"五星红旗迎风飘扬，毛主席的照片挂在墙上\"";

        boolean contains = SensitiveWordHelper.contains(text);

        // 查找第一个敏感词
        String firstWord = SensitiveWordHelper.findFirst(text);

// 查找所有敏感词
        List<String> allWords = SensitiveWordHelper.findAll(text);

        System.out.println(allWords);

    }

    @Test
    public void test2(){
        System.out.println(wordBs.contains("人工智能"));
    }
}
