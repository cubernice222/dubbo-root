package com.carrot.biz.impl;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DubboProxy implements InvocationHandler {
    private CamelContext camelContext;
    public DubboProxy(Logger logger) {
        this.logger = logger;
    }

    public CamelContext getCamelContext() {
        return camelContext;
    }

    public void setCamelContext(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public DubboProxy(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    private Logger logger = LoggerFactory.getLogger(DubboProxy.class);

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object transform = null;
        if(args != null && args.length > 0){
            transform = args.length == 1?args[0]:args;
        }

        ProducerTemplate template = camelContext.createProducerTemplate();
        Object obj = template.requestBody("direct:userService", transform);
        return obj;
    }
}
