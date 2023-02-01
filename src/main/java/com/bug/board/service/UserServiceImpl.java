package com.bug.board.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bug.board.domain.User;
import com.bug.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	@Override
	public User findByUsername(String username) {
		
		User user = userRepository.findByUsername(username).orElseThrow(new Supplier<UsernameNotFoundException>() {

			@Override
			public UsernameNotFoundException get() {
				return new UsernameNotFoundException("해당 아이디는 존재하지 않습니다. : " + username);
			}
		});
		
		return user;
	}

	@Override
	public User save(User user) {
		
		String rawPWD = user.getPassword();
		String encPWD = encoder.encode(rawPWD);
		user.setPassword(encPWD);
		user.setRoleType("ROLE_USER");
		user.setCreateDate(Timestamp.valueOf(getDateTime()));
		return userRepository.save(user);
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
