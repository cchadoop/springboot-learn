package com.ontop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontop.entity.UserInfo;
import com.ontop.repository.UserInfoRepository;

@RestController
public class UserInfoController {
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@GetMapping("save")
	public String save() {
		UserInfo userInfo = new UserInfo(System.currentTimeMillis(),"用户"+System.currentTimeMillis(),"123");
		userInfoRepository.save(userInfo);
        return "success";
	}
	
}
