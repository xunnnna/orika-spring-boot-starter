package com.example.orika.config;

import ma.glasnost.orika.MapperFactory;

/**
 * 自定义Mapper
 * The interface to configure {@link MapperFactory}.
 */
public interface OrikaMapperFactoryConfigurer {

    /**
     * Configures the {@link MapperFactory}.
     *
     * @param orikaMapperFactory the {@link MapperFactory}.
     */
    void configure(MapperFactory orikaMapperFactory);

}
