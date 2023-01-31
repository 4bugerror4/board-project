package com.bug.board.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.bug.board.domain.Board;
import com.bug.board.domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		
		User u = (User) obj;
		if (StringUtils.isEmpty(u.getUsername())) {
			e.rejectValue("username", "", "아이디 입력하세요");
		} else if (StringUtils.isEmpty(u.getPassword())) {
			e.rejectValue("password", "", "비밀번호 입력하세요");
		} else if (StringUtils.isEmpty(u.getEmail())) {
			e.rejectValue("email", "", "이메일 입력하세요");
		}
		
	}

}
