package com.ontop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ontop.Dao.UserMapper;
import com.ontop.Entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	
	//查询
	@GetMapping("/findAll")
	public List<User> findAll(){
		return userMapper.selectList(null);
	}
	@GetMapping("/findOne/{id}")
	public User findOne(@PathVariable("id") long id) {
		return userMapper.selectById(id);
	}
	
	@PostMapping("/insert")
	public String insert(User user) {
		user.setName("赵六");
		user.setAge(20);
		user.setBirthday("2017-01-20");
		userMapper.insert(user);
		return "success";
	}
	/* $.ajax({
	 * 		url:'/delete/RowId'
	 * 		type:'post'
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
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		userMapper.deleteById(id);
		return "success";
	}
	
	@PutMapping("/update")
	public String delete(User user) {
		user.setId(1l);
		user.setName("王五");
		userMapper.updateById(user);
		return "success";
	}
}
