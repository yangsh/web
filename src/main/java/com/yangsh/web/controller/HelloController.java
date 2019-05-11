package com.yangsh.web.controller;

import com.yangsh.web.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: TODO
 * <p></p>
 * Author: shaoye
 * Date: 2019-05-10 19:51
 */
@RestController
public class HelloController {

    @Resource
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello() {
        return helloService.hello();
    }

}
