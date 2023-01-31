package com.bug.board.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bug.board.domain.User;
import com.bug.board.service.UserService;
import com.bug.board.validator.UserValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AccountController {
	
	private final UserService userService;
	private final UserValidator userValidator;
	private final BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	@GetMapping("/auth/account/login")
	public String loginForm() {
		return "account/login";
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/auth/account/register")
	public String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "account/register";
	}
	
	@Transactional
	@PostMapping("/auth/account/register")
	public String registerSave(@Valid User user, BindingResult bindingResult) {
		
		userValidator.validate(user, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return "account/register";
		}
		
		String rawPWD = user.getPassword();
		String encPWD = encoder.encode(rawPWD);
		user.setPassword(encPWD);
		user.setRoleType("ROLE_USER");
		user.setCreateDate(Timestamp.valueOf(getDateTime()));
		
		userService.save(user);
		
		return "redirect:/";
	}
	
	private String getDateTime() {

		LocalDate nowDate = LocalDate.now();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatedNowDate = nowDate.format(formatterDate);

		LocalTime nowTime = LocalTime.now();
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formatedNowTime = nowTime.format(formatterTime);

		String dateTime = formatedNowDate + " " + formatedNowTime;

		return dateTime;
	}


}
