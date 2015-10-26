package com.test.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.entity.User;

@Controller
@RequestMapping("myajax.do")
public class MyAjaxController {
	
	/*处理Ajax请求，加@ResponseBody即可*/
	@RequestMapping(params="method=test2",method=RequestMethod.GET)
	public @ResponseBody List<User> test2(String uname) throws Exception{ 
		String uname2 = new String(uname.getBytes("iso8859-1"),"gbk");
		System.out.println(uname2); 
		System.out.println("MyAjaxController.test2()");
		List<User> list = new ArrayList<User>();
		User user1 = new User();
		user1.setUsername("高淇");
		user1.setPassword("123");
		list.add(user1);
		return list;
	}
}
	
	/*@RequestMapping(params="method=test1",method=RequestMethod.GET)*/
	/*public @ResponseBody List<User2> test1(String uname) throws Exception{ 
		String uname2 = new String(uname.getBytes("iso8859-1"),"gbk");
		System.out.println(uname2); 
		System.out.println("MyAjaxController.test1()");
		List<User2> list = new ArrayList<User2>();
		User user1 = new User();
		user1.setUsername("高淇");
		user1.setPassword("123");
		User user2 = new User();
		list.add(new User2("高淇","123"));
		list.add(new User2("马士兵","456"));
		
		return list;
	}*/
