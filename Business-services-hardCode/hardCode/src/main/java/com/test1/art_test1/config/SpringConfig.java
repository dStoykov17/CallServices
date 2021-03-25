package com.test1.art_test1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SpringConfig implements WebMvcConfigurer {

    private final WebApplicationContext wac;


    @Autowired
    public SpringConfig(WebApplicationContext wac) {

        Assert.notNull(wac, "WebApplicationContext mus be init.");
        this.wac = wac;
    }

    @Bean(name = {"validator", "mvcValidator"})
    public LocalValidatorFactoryBean validator() {

        return new LocalValidatorFactoryBean();
    }


    @Override
    public org.springframework.validation.Validator getValidator() {


        LocalValidatorFactoryBean validator = wac.getAutowireCapableBeanFactory().getBean(LocalValidatorFactoryBean.class);
        Assert.notNull(validator, "Validator must be init.");
        return validator;
    }


    @Bean(name = "methodValidationPostProcessor")
    public static MethodValidationPostProcessor methodValidationPostProcessor
    (
            @Qualifier("validator") LocalValidatorFactoryBean validator
    ) {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();

        methodValidationPostProcessor.setValidatorFactory(validator); //localValidatorFactoryBean.getValidator() should be return javax.validation.Validator

        return methodValidationPostProcessor;
    }
}
