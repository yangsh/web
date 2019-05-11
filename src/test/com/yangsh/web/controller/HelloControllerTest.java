package com.yangsh.web.controller;

import com.yangsh.web.Application;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.xml.Parser;

import javax.annotation.Resource;

/**
 * Description: TODO
 * <p></p>
 * Author: shaoye
 * Date: 2019-05-11 10:00
 */
@SpringBootTest(classes = {Application.class})
public class HelloControllerTest extends AbstractTestNGSpringContextTests {

    @Resource
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeClass
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testHello() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(content, "Hello");
    }


}
