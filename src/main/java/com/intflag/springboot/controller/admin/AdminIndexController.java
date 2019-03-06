package com.intflag.springboot.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminIndexController {

	@GetMapping("/admin")
	public ModelAndView showIndex() {
		ModelAndView view = new ModelAndView("admin/index");
		return view;
	}
}
