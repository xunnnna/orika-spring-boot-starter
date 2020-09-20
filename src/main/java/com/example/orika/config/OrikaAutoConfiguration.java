package com.example.orika.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.MapperFactoryBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The auto-configuration for Orika.
 */
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "orika.enabled", matchIfMissing = true)
@EnableConfigurationProperties(OrikaProperties.class)
@Configuration
public class OrikaAutoConfiguration {

    /**
     * The configuration properties for Orika.
     */
    private final OrikaProperties orikaProperties;

    /**
     * The configurers for {@link MapperFactoryBuilder}.
     */
    private final Optional<List<OrikaMapperFactoryBuilderConfigurer>> orikaMapperFactoryBuilderConfigurers;

    /**
     * The configurers for {@link MapperFactory}.
     */
    private final Optional<List<OrikaMapperFactoryConfigurer>> orikaMapperFactoryConfigurers;

    private final Optional<List<OrikaMapperConverterConfigurer>> orikaMapperConverterConfigurers;

    /**
     * Creates a {@link MapperFactoryBuilder}.
     *
     * @return a {@link MapperFactoryBuilder}.
     */
    @ConditionalOnMissingBean
    @Bean
    public MapperFactoryBuilder<?, ?> orikaMapperFactoryBuilder() {
        DefaultMapperFactory.Builder orikaMapperFactoryBuilder = new DefaultMapperFactory.Builder();
        if (orikaProperties.getUseBuiltinConverters() != null) {
            orikaMapperFactoryBuilder.useBuiltinConverters(orikaProperties.getUseBuiltinConverters());
        }
        if (orikaProperties.getUseAutoMapping() != null) {
            orikaMapperFactoryBuilder.useAutoMapping(orikaProperties.getUseAutoMapping());
        }
        if (orikaProperties.getMapNulls() != null) {
            orikaMapperFactoryBuilder.mapNulls(orikaProperties.getMapNulls());
        }
        if (orikaProperties.getDumpStateOnException() != null) {
            orikaMapperFactoryBuilder.dumpStateOnException(orikaProperties.getDumpStateOnException());
        }
        if (orikaProperties.getFavorExtension() != null) {
            orikaMapperFactoryBuilder.favorExtension(orikaProperties.getFavorExtension());
        }
        if (orikaProperties.getCaptureFieldContext() != null) {
            orikaMapperFactoryBuilder.captureFieldContext(orikaProperties.getCaptureFieldContext());
        }
        orikaMapperFactoryBuilderConfigurers
                .orElseGet(Collections::emptyList)
                .forEach(configurer -> configurer.configure(orikaMapperFactoryBuilder));
        log.debug("Created a MapperFactoryBuilder: [{}]", orikaMapperFactoryBuilder);
        return orikaMapperFactoryBuilder;
    }

    /**
     * Creates a {@link MapperFactory}.
     *
     * @param orikaMapperFactoryBuilder the {@link MapperFactoryBuilder}.
     * @return a {@link MapperFactory}.
     */
    @ConditionalOnMissingBean
    @Bean
    public MapperFactory orikaMapperFactory(MapperFactoryBuilder<?, ?> orikaMapperFactoryBuilder) {
        MapperFactory orikaMapperFactory = orikaMapperFactoryBuilder.build();
        orikaMapperFactoryConfigurers
                .orElseGet(Collections::emptyList)
                .forEach(configurer -> configurer.configure(orikaMapperFactory));
        log.debug("Created a MapperFactory: [{}]", orikaMapperFactory);
        return orikaMapperFactory;
    }

    @ConditionalOnMissingBean
    @Bean
    public ConverterFactory orikaConverterFactory(MapperFactory orikaMapperFactory) {
        ConverterFactory converterFactory = orikaMapperFactory.getConverterFactory();
        orikaMapperConverterConfigurers
                .orElseGet(Collections::emptyList)
                .forEach(configurer -> configurer.configure(converterFactory));
        log.debug("configured ConverterFactory: [{}]", converterFactory);
        return converterFactory;
    }

    /**
     * Creates a {@link MapperFacade}.
     *
     * @param orikaMapperFactory the {@link MapperFactory}.
     * @return a {@link MapperFacade}.
     */
    @ConditionalOnMissingBean
    @Bean
    public MapperFacade orikaMapperFacade(MapperFactory orikaMapperFactory) {
        MapperFacade orikaMapperFacade = orikaMapperFactory.getMapperFacade();
        log.debug("Created a MapperFacade: [{}]", orikaMapperFacade);
        return orikaMapperFacade;
    }

}
