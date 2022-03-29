package com.ke.live.config.Dozer;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author 小凡
 */
@Configuration
@EnableAutoConfiguration
public class DozerBeanMapperConfigure {

    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Arrays.asList("dozerBeanMapping.xml"));
        return mapper;
    }

}
