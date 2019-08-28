package com.ontop.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ontop.dao.User;
import com.ontop.repository.UserRepository;
import java.util.List;

@RestController
@RequestMapping(value = "/swagger")
// @Api(value = "用户操作接口", tags = { "用户操作接口" })
public class UserSwagger {

	@Autowired
	UserRepository userRepository;

	@ApiOperation(value = "获取用户详细信息", notes = "根据用户的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
	@GetMapping(value = "user/{id}")
	public User findById(@PathVariable(value = "id") Integer id) {
		User user = userRepository.getOne(id);
		return user;
	}

	@ApiOperation(value = "获取用户列表", notes = "获取用户列表")
	@GetMapping(value = "/users")
	public List<User> getUserList() {
		return userRepository.findAll();
	}

	@ApiOperation(value = "保存用户", notes = "保存用户")
	@PostMapping(value = "/user")
	public String saveUser(@RequestBody @ApiParam(name = "用户对象", value = "传入json格式", required = true) User user) {
		userRepository.save(user);
		return "success!";
	}

	@ApiOperation(value = "修改用户", notes = "修改用户")
//	@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer"),
//			@ApiImplicitParam(name = "username", value = "用户名称", required = true, paramType = "query", dataType = "String"),
//			@ApiImplicitParam(name = "password", value = "用户密码", required = true, paramType = "query", dataType = "String") })
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Integer", paramType = "path"),
		@ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User") })
	@PutMapping(value = "/user/{id}")
	public String updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		User u = userRepository.getOne(id);
		u.setUserName(user.getUserName());
		u.setUserPassword(user.getUserPassword());
		userRepository.save(u);
		return "success!";
	}

	@ApiOperation(value = "删除用户", notes = "根据用户的id来删除用户")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "path", dataType = "Integer")
	@DeleteMapping(value = "/user/{id}")
	public String deleteUserById(@PathVariable(value = "id") Integer id) {
		User user = userRepository.getOne(id);
		userRepository.delete(user);
		return "success!";
	}
}
