package com.bug.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Transactional(readOnly = true)
	@GetMapping("/login")
	public String loginForm() {
		return "account/login";
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/register")
	public String registerForm() {
		return "account/register";
	}

}
