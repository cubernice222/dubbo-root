package com.carrot.controller;

import com.carrot.connector.biz.UserService;
import com.carrot.connector.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TriggerController {
    @Autowired(required = false)
    private UserService userService;

    @ResponseBody
    @RequestMapping("/{name}.json")
    public User getUserByName(@PathVariable("name") String name){
        return userService.getUserByName(name);
    }
}
