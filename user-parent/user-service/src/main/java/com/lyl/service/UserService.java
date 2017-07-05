package com.lyl.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lyl.vo.User;

@Service
public class UserService {
	
  private static Map<String,User>  userMap=new HashMap<String,User>();
  
  
  public void add(User user){
      System.out.println("ffff");
	  userMap.put(user.getId(), user);
  }
  
  public User getUserById(String id){
     System.out.println("fff");
	 return userMap.get(id);
  }
  
}
