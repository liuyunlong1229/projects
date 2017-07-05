package com.lyl.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyl.service.UserService;
import com.lyl.vo.User;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/add")
	public void add(String id,String name){
		Assert.hasLength(id,"用户编号不能为空");
		Assert.hasLength(name ,"用户名称不能为空");
		User user=new User(id,name);
		userService.add(user);
		
		
	}
	
	
	@RequestMapping("/user/{id}")
	public void getUserbyId(@PathVariable("id") String id){
		Assert.hasLength(id,"用户编号不能为空");
		User user=userService.getUserById(id);
		System.out.println(user);
		
	}
}
