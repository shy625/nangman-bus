package com.nangman.api.Service;

import com.nangman.api.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nangman.db.entity.User;
import com.nangman.db.repository.UserRepository;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(@Lazy UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User createUser(UserDto.RegisterRequest userRegisterInfo) {
		User user = new User();
		user.setUseremail(userRegisterInfo.getUseremail());
		user.setUserBirthday(userRegisterInfo.getUserBirthday());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User getUserByUserId(long userId) {
		log.debug(userRepository.findUserByUserId(userId).get().toString());
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		return userRepository.findUserByUserId(userId).get();
	}

	@Override
	public User getUserByUseremail(String useremail) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		return userRepository.findUserByUseremail(useremail).get();
	}

	@Override
	public User deleteuser(long userId) {
		User user = userRepository.findUserByUserId(userId).get();
		user.setDeleted(true);
		userRepository.save(user);

		return user;
	}

	@Override
	public User updateUser(UserDto.RegisterRequest userInfo) {
		log.info(userInfo.getUseremail());

		User user = userRepository.findUserByUseremail(userInfo.getUseremail()).get();


		// 생일 값 추가일 경우에만 수행
		if(user.getUserBirthday() == null && userInfo.getUserBirthday() != null) user.setUserBirthday(user.getUserBirthday());

		// 비밀번호 수정일 경우에만 수행
		if(!passwordEncoder.matches(userInfo.getPassword(), user.getPassword())) user.setPassword(passwordEncoder.encode(userInfo.getPassword()));

		userRepository.save(user);

		return user;
	}

}
