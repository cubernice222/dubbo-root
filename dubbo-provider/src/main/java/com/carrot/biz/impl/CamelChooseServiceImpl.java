package com.carrot.biz.impl;

import com.carrot.biz.CamelChooseService;
import com.carrot.connector.model.User;
import org.apache.camel.Consume;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("camelChooseService")
public class CamelChooseServiceImpl implements CamelChooseService{

    private Logger logger = LoggerFactory.getLogger(CamelChooseServiceImpl.class);
    @Override
    @Consume(uri = "direct:cuber")
    public User nameEq_cuber(String name) {
        logger.info("{}",name);
        User user = new User();
        user.setName("cuber");
        user.setAge(20);
        return user;
    }

    @Override
    @Consume(uri = "direct:eva")
    public User nameEq_eva(String name) {
        logger.info("{}",name);
        User user = new User();
        user.setName("eva");
        user.setAge(18);
        return user;
    }

    @Override
    @Consume(uri = "direct:otherwise")
    public User otherwise(String name) {
        logger.info("{}",name);
        User user = null;
        if(name != null){
            user = new User();
            user.setName(name);
            user.setAge(24);
        }
        return user;
    }
}
