package com.mercata.inventarium.Configuration;

import org.modelmapper.ModelMapper;
// import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    
    /*@Bean
    public JacksonJsonMessageConverter jsonMessageConverter() {
        JacksonJsonMessageConverter jsonConverter = new JacksonJsonMessageConverter();

        return jsonConverter;
    }*/
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
