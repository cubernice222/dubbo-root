package com.carrot.config;

import com.alibaba.dubbo.config.spring.ServiceBean;
import com.carrot.biz.impl.DubboProxy;
import com.carrot.util.PackageScan;
import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;

import java.lang.reflect.Proxy;
import java.util.Set;

@Configuration
@ImportResource({"classpath:dubbo/dubbo-provider.xml","classpath:CamelContext.xml"})
public class DubboConfig implements BeanDefinitionRegistryPostProcessor,ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(DubboConfig.class);
    private BeanDefinitionRegistry registry;

    private ApplicationContext application;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        this.registry = beanDefinitionRegistry;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @EventListener
    public void init(ApplicationReadyEvent event)throws Exception{
        Set<Class<?>> classes = PackageScan.getClasses("com.carrot.connector.biz");
        CamelContext camelContext = application.getBean(CamelContext.class);
        if(classes != null && !classes.isEmpty()){
            classes.stream().forEach(aClass -> {
                if(aClass.isInterface()){
                    Object beanObj = Proxy.newProxyInstance(aClass.getClassLoader(),new Class[]{aClass},new DubboProxy(camelContext));
                    ServiceBean<Object> serviceConfig = new ServiceBean<>();
                    serviceConfig.setApplicationContext(application);
                    try {
                        serviceConfig.afterPropertiesSet();
                    } catch (Exception e) {
                        logger.error("after properties set:",e);
                    }
                    serviceConfig.setInterface(aClass);
                    serviceConfig.setRef(beanObj);
                    serviceConfig.export();
                }
            });
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.application =applicationContext;
    }
}