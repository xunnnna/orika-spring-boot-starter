package com.example.orika.mapper;

import com.example.orika.config.OrikaMapperFactoryConfigurer;
import com.example.orika.domain.Person;
import com.example.orika.dto.PersonDTO;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;


@Component
public class PersonMapper implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(Person.class, PersonDTO.class)
                .field("firstName", "givenName")
                .field("lastName", "sirName")
                .byDefault()
                .register();
    }

}
