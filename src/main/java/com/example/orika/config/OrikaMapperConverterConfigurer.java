package com.example.orika.config;

import ma.glasnost.orika.converter.ConverterFactory;

/**
 * 自定义类型converter
 * Created by zhutingxuan on 2020/9/20.
 */
public interface OrikaMapperConverterConfigurer {

    void configure(ConverterFactory orikaConverterFactory);

}
