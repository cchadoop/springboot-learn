package com.ontop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.ontop.entity.UserInfo;
import com.ontop.repository.UserInfoRepository;
import com.ontop.service.UserService;

@RestController
public class UserInfoController {
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private UserService userService;

	// JPA
	@GetMapping("saveByJpa")
	public String saveByJpa() {
		UserInfo userInfo = new UserInfo(System.currentTimeMillis(), "用户" + System.currentTimeMillis(), "123");
		userInfoRepository.save(userInfo);
		return "success";
	}

	@GetMapping("deleteByJpa")
	public String deleteByJpa(Long id) {
		userInfoRepository.deleteById(id);
		return "success";
	}

	@GetMapping("updateByJpa")
	public String updateByJpa(UserInfo userInfo) {
		userInfoRepository.save(userInfo);
		return "success";
	}

	@GetMapping("findAllByJpa")
	public List<UserInfo> findAllByJpa() {
		List<UserInfo> list = userInfoRepository.findAll();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("findOneByJpa")
	public Optional findOneByJpa(Long id) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		ExampleMatcher matcher = ExampleMatcher.matching();
		Example example = Example.of(userInfo, matcher);
		return userInfoRepository.findOne(example);
	}

	@GetMapping("findOne2ByJpa")
	public UserInfo findOne2ByJpa(Long id) {
		Optional<UserInfo> optional = userInfoRepository.findById(id);
		UserInfo userInfo = optional.get();
		return userInfo;
	}

	// Service MongoTemplate
	@GetMapping("save")
	public String save() {
		UserInfo userInfo = new UserInfo(System.currentTimeMillis(), "用户" + System.currentTimeMillis(), "234");
		userService.saveUser(userInfo);
		return "success";
	}

	@GetMapping("delete")
	public String delete(Long id) {
		DeleteResult result = userService.deleteUser(id);
		if (result.getDeletedCount() == 1) {
			return "success";
		} else {
			return "fail";
		}

	}

	@GetMapping("update")
	public String update(UserInfo user) {
		UpdateResult result = userService.modifyUser(user);
		if (result.getModifiedCount() == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

	@GetMapping("findAll")
	public List<UserInfo> findAll(UserInfo user) {
		List<UserInfo> list = userService.findAll(user);
		return list;
	}

	@GetMapping("findOne")
	public UserInfo findOne(Long id) {
		UserInfo userInfo = userService.findById(id);
		return userInfo;
	}

}
