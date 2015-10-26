package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AlgorithmController {
	@RequestMapping("algorithm")
	public ModelAndView MRMD(Integer initial,Integer times,Integer subtractor,Integer maximum,
			String optimal,String pcaac,String scaac,String d,
			String libd3c,String randomforest,String libsvm,String bagging,String ibk) {
		System.out.println(initial);
		System.out.println(times);
		System.out.println(subtractor);
		System.out.println(maximum);
		System.out.println(optimal);
		System.out.println(pcaac);
		System.out.println(scaac);
		System.out.println(d);
		return new ModelAndView("404");
	}
}
