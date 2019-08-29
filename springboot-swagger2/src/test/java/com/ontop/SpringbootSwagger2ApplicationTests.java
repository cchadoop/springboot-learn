package com.ontop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebAppConfiguration
public class SpringbootSwagger2ApplicationTests {
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	@Before
    public void initMockMvc() {
		mockMvc=MockMvcBuilders.webAppContextSetup(context).build();
    }
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testGetUserList() throws Exception {
		MvcResult result=mockMvc.perform(MockMvcRequestBuilders.get("/swagger/users")).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String content = response.getContentAsString();
		System.out.println(content);
	}

}
