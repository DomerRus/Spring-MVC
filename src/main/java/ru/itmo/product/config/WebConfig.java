package ru.itmo.product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"ru.itmo.product"})
public class WebConfig {

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:/templates/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

//    @Autowired
//    private ApplicationContext applicationContext;
//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
//
//        templateResolver.setApplicationContext(applicationContext);
//        templateResolver.setPrefix("classpath:/templates/");
//        templateResolver.setSuffix(".html");
//
//        return templateResolver;
//
//    }
//
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.setEnableSpringELCompiler(true);
//
//        return templateEngine;
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        ViewResolverRegistry registry = new ViewResolverRegistry(null, applicationContext);
//
//        resolver.setTemplateEngine(templateEngine());
//        registry.viewResolver(resolver);
//
//        return resolver;
//    }
}