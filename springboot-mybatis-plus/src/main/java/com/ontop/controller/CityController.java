package com.ontop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ontop.entity.City;
import com.ontop.entity.User;
import com.ontop.mapper.CityMapper;

@RestController
public class CityController {

	@Autowired
	private CityMapper cityMapper;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/citys")
	public List<City> findAll(){
		//得到用户信息
		User user = restTemplate.getForObject("http://localhost:8888/users/{id}", User.class,1l);
		System.out.println(user);
		return cityMapper.selectList(null);
	}
}
