package com.nangman.api.service;

import com.nangman.api.dto.UserDto;
import com.nangman.db.repository.UserRepositoryImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nangman.db.entity.User;
import com.nangman.db.repository.UserRepository;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	private UserRepositoryImpl userRepositoryImpl;

	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(@Lazy UserRepository userRepository, @Lazy UserRepositoryImpl userRepositoryImpl, @Lazy PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userRepositoryImpl = userRepositoryImpl;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User createUser(UserDto userRegisterInfo) {
		User user = new User();
		user.setUseremail(userRegisterInfo.getUseremail());
		user.setUserBirthday(userRegisterInfo.getBirthday());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getUserByUseremail(String useremail) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositoryImpl.findUserByUseremail(useremail).get();
		return user;
	}
}
