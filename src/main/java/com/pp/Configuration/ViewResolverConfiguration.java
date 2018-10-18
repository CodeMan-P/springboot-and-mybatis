package com.pp.Configuration;

import com.pp.Interceptor.DefaultInterceptor;
import com.pp.springbootandmybatis.SpringbootAndMybatisApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

/**
 * 主要配置多视图实现的视图解析器相关bean实例
 * 其实关键点在于两个：
 * 1、配置order属性
 * 2、配置viewnames属性
 * <p>
 * 注意：
 * return new ModelAndView("jsps/index");//或者return "jsps/index"
 * 对应 /WEB-INF/jsps/index.jsp
 * ==========================
 * 同理：
 * return "thymeleaf/index";//或者return “thymeleaf/index”
 * 对应 /WEB-INF/thymeleaf/index.html
 */
@Configuration
@ComponentScan(basePackages = "com.pp", basePackageClasses = SpringbootAndMybatisApplication.class, useDefaultFilters = true)
@EnableWebMvc
public class ViewResolverConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");
//            resolver.setSuffix(".jsp");
//        resolver.setViewNames("jsps/*");
//            resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        resolver.setViewNames("*");
        resolver.setOrder(2);
        //resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置默认首页
        registry.addViewController("/").setViewName("forward:/WEB-INF/index.jsp");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("/WEB-INF/classes/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // templateEngine
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolverThymeLeaf() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("utf-8");
        viewResolver.setOrder(1);
        //viewResolver.setViewNames(new String[]{"thyme/*"});
        viewResolver.setViewNames(new String[]{"thymeleaf/*", "vue/*"});
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new DefaultInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
        //.excludePathPatterns("/index");
    }

//        @Bean
//        public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
//            ConfigurableEmbeddedServletContainer factory = new TomcatEmbeddedServletContainerFactory();
//            factory.setDocumentRoot(new File("D:\\MyEclipse\\Workspaces\\springboot-and-mybatis\\src\\main\\WebRoot\\"));
//            return (EmbeddedServletContainerFactory) factory;
//        }
}