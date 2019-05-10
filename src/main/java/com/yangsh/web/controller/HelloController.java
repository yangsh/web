package com.yangsh.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: TODO
 * <p></p>
 * Author: shaoye
 * Date: 2019-05-10 19:51
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello XXX YYY ZZZ PPP";
    }

}
