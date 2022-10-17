package com.ke.live.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 小凡
 */
@Component
@Data
public class DataSourceConfiguration implements InitializingBean {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public static String URL;
    public static String USERNAME;
    public static String PASSWORD;

    @Override
    public void afterPropertiesSet() throws Exception {
        URL = url;
        USERNAME = username;
        PASSWORD = password;
    }
}
