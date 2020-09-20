package com.example.orika.mapper.converter;

import com.example.orika.config.OrikaMapperConverterConfigurer;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by zhutingxuan on 2020/9/20.
 */
@Component
public class InstantConverter extends BidirectionalConverter<Instant, String> implements OrikaMapperConverterConfigurer {

    @Override
    public String convertTo(Instant source, Type<String> destinationType, MappingContext mappingContext) {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(source, ZoneId.systemDefault());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return zonedDateTime.format(dateTimeFormatter);
    }

    @Override
    public Instant convertFrom(String source, Type<Instant> destinationType, MappingContext mappingContext) {
        source += "T00:00:00Z";
        return Instant.parse(source);
    }

    @Override
    public void configure(ConverterFactory orikaConverterFactory) {
        orikaConverterFactory.registerConverter(this);
    }
}
