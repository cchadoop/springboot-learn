package com.ontop;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.ontop.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRestfulApplicationTests {

	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testRestTemplate() {
		User user = restTemplate.getForObject("http://localhost:8888/users/{id}", User.class, 1l);
		System.out.println(user);
	}

	@Test
	public void testRestTemplate2() {
		User[] users = restTemplate.getForObject("http://localhost:8888/users", User[].class);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testRestTemplate3() {
		// Create a list for the message converters
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        // Add the Jackson Message converter
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        // Add the message converters to the restTemplate
        restTemplate.setMessageConverters(messageConverters);
		// Prepare acceptable media type
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        // acceptableMediaTypes.add(MediaType.APPLICATION_XML);
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
		HttpEntity<Object> entity = new HttpEntity<Object>(headers);
		//TODO
		restTemplate.exchange("http://localhost:8888/users", HttpMethod.POST, entity , User.class);
	}
}
