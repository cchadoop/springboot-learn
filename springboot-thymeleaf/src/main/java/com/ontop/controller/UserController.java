package com.ontop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.ontop.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/")
	public String testThymeleaf(ModelMap map) {
		map.addAttribute("msg", "Hello thymeleaf");
		User user = new User(1l, "张三",new Date());
		map.addAttribute("user", user);
		List<User> userList = new ArrayList<>();
		userList.add(new User(1l, "张三",new Date()));
		userList.add(new User(2l, "李四",new Date()));
		userList.add(new User(3l, "王五",new Date()));
		map.addAttribute("userList", userList);
		return "thymeleaf";
	}
	
	@GetMapping("/international")
	public String testInternational(ModelMap map) {
		Locale locale = LocaleContextHolder.getLocale();
		map.addAttribute("message", messageSource.getMessage("message", null, locale));
		return "international";
	}
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
}
