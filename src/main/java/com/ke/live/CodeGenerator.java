package com.ke.live;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;


/**
 * @author 小凡
 * @date 2022/7/4
 * mybatis-plus 代码构造器
 */
public class CodeGenerator {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(LiveApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        String url = environment.getProperty("spring.datasource.url" );
        String username = environment.getProperty("spring.datasource.username" );
        String password = environment.getProperty("spring.datasource.password" );

        String projectPath = System.getProperty("user.dir" );

        FastAutoGenerator.create(url, username, password)
                .globalConfig(
                        builder -> builder.author("kzf" )
                                .fileOverride()
                                .outputDir(projectPath + "/src/main/java" ))
                .packageConfig(
                        builder -> builder.parent("com.ke.live" )
                                .entity("entity" )
                                .mapper("mapper" )
                                .service("service" )
                                .serviceImpl("serviceImpl" ))
                .templateConfig(
                        builder -> builder.service("templates/service.java" )
                                .serviceImpl("templates/serviceImpl.java" )
                                .mapper("templates/mapper.java" )
                                .entity("templates/entity.java" )
                                .mapperXml("" )
                                .controller("" ))
                .strategyConfig(
                        builder -> builder.addTablePrefix("tb_" )
                                .addInclude("hotspot_mini_course" ))
                .execute();

    }

}
