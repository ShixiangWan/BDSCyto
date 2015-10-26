package com.test.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.dao.IUserDAO;

@Controller
@RequestMapping("user")
public class UserLoginController {
	
	@Resource
	private IUserDAO iUserDAO;
	
	@RequestMapping(params="method=reg")
	public ModelAndView checkLogin(String uname, String psword) {
		boolean flag = iUserDAO.checkByInfo(uname, psword);
		if (flag) {
			System.out.println(flag);
			return new ModelAndView("index");
		} else {
			return new ModelAndView("404");
		}
	}
	
	@RequestMapping(params="method=reg2")
	public ModelAndView checkLogin2(String uname, String psword, Model map) {
		boolean flag = iUserDAO.checkByInfo(uname, psword);
		if (flag) {
			System.out.println(flag);
			map.addAttribute("a", "aaa");
			return new ModelAndView("index");
		} else {
			return new ModelAndView("404");
		}
	}
	
}
