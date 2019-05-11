package com.yangsh.web.service;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Description: TODO
 * <p></p>
 * Author: shaoye
 * Date: 2019-05-11 23:19
 */
public class HelloServiceTest {

    @Test
    public void testHello() {
        HelloService service = new HelloService();

        Assert.assertEquals(service.hello(), "Hello");
    }

}
