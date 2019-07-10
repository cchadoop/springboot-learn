package com.ontop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontop.entity.User;
import com.ontop.mapper.UserMapper;

@RestController
public class UserController {
	
	/*
	 * Restful开发接口命名规范
	 *  URL请求采用小写字母，数字，部分特殊符号（非制表符）组成
	 *  URL请求中不采用大小写混合的驼峰命名方式，尽量采用全小写单词，如果需要连接多个单词，则采用连接符“_”连接单词
	 *  
	 *  CRUD命名规范
	 *  如果为资源分类，则按照RESTful的方式定义第三级Pattern，
	 *  RESTful规范中，资源必须采用资源的名词复数定义。
	 *  大于三级的话，用？拼接
	 */

	@Autowired
	private UserMapper userMapper;
	
	//查询
	@GetMapping("/users")
	public List<User> findAll(){
		return userMapper.selectList(null);
	}
	@GetMapping("/users/{id}")
	public User findOne(@PathVariable("id") long id) {
		return userMapper.selectById(id);
	}
	
	@PostMapping("/users")
	public String insert(User user) {
		user.setName("赵六");
		user.setAge(20);
		user.setBirthday("2017-01-20");
		userMapper.insert(user);
		return "success";
	}
	/* $.ajax({
	 * 		url:'/delete/RowId',
	 * 		type:'post',
	 *		//type:'delete' 不加_method参数
	 * 		data:{
	 * 			_method:"DELETE",
	 * 			id:RowId
	 * 		}
	 * 		dataType:'json'
	 * 		success:function(result){
	 * 
	 * 		}
	 * });
	 * 
	 * 
	 */
	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable("id") long id) {
		userMapper.deleteById(id);
		return "success";
	}
	
	@PutMapping("/users")
	public String delete(User user) {
		user.setId(1l);
		user.setName("王五");
		userMapper.updateById(user);
		return "success";
	}
}
