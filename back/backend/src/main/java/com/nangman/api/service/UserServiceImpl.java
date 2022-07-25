package com.nangman.api.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nangman.api.request.UserRegisterPostReq;
import com.nangman.db.entity.User;
import com.nangman.db.repository.UserRepository;
import com.nangman.db.repository.UserRepositorySupport;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	private UserRepositorySupport userRepositorySupport;

	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(@Lazy UserRepository userRepository, @Lazy UserRepositorySupport userRepositorySupport, @Lazy PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userRepositorySupport = userRepositorySupport;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();
		user.setUseremail(userRegisterInfo.getUseremail());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(String useremail) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByUseremail(useremail).get();
		return user;
	}
}
