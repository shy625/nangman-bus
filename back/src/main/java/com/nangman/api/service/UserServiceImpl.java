package com.nangman.api.service;

import com.nangman.api.dto.UserDto;
import com.nangman.db.entity.Nickname;
import com.nangman.db.entity.Setting;
import com.nangman.db.repository.NicknameRepository;
import com.nangman.db.repository.SettingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nangman.db.entity.User;
import com.nangman.db.repository.UserRepository;

import java.util.Random;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private NicknameRepository nicknameRepository;

	private SettingRepository settingRepository;
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(@Lazy UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, @Lazy SettingRepository settingRepository, @Lazy NicknameRepository nicknameRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.nicknameRepository = nicknameRepository;
		this.settingRepository = settingRepository;

	}

	@Override
	public User createUser(UserDto.RegisterRequest userRegisterInfo) {
		User user = new User();
		user.setUseremail(userRegisterInfo.getUseremail());
		user.setUserBirthday(userRegisterInfo.getUserBirthday());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));

		Setting setting = new Setting();
		setting.setUser(user);
		settingRepository.save(setting);
		log.info(setting.isWhisperMode() ? "Y" : "N");

		user.setSetting(setting);
		// 닉네임 설정(random 선택)
//		long totalNickname = nicknameRepository.count();
//		user.setNickname(nicknameRepository.getOne((long) (Math.random() * totalNickname + 1)));

		userRepository.save(user);
		return userRepository.findByUseremailAndIsDeletedFalse(user.getUseremail()).get();
	}

	@Override
	public User getUserByUserId(long userId) {

		// 디비에 유저 정보 조회 (userId를 통한 조회).
		return userRepository.findByIdAndIsDeletedFalse(userId).get();
	}

	@Override
	public User getUserByUseremail(String useremail) {
		// 디비에 유저 정보 조회 (useremail을 통한 조회).
		return userRepository.findByUseremailAndIsDeletedFalse(useremail).get();
	}

	@Override
	public User deleteUser(long userId) {
		User user = userRepository.findByIdAndIsDeletedFalse(userId).get();
		user.setDeleted(true);
		userRepository.save(user);
		return user;
	}

	@Override
	public User updateUser(UserDto.RegisterRequest userInfo) {
		log.info(userInfo.getUseremail());

		User user = userRepository.findByUseremailAndIsDeletedFalse(userInfo.getUseremail()).get();


		// 생일 값 추가일 경우에만 수행
		if(user.getUserBirthday() == null && userInfo.getUserBirthday() != null) user.setUserBirthday(user.getUserBirthday());

		// 비밀번호 수정일 경우에만 수행
		if(!passwordEncoder.matches(userInfo.getPassword(), user.getPassword())) user.setPassword(passwordEncoder.encode(userInfo.getPassword()));

		userRepository.save(user);


		return user;
	}

	@Override
	public User updateUserIsRouletted(long userId) {
		User user = userRepository.findByIdAndIsDeletedFalse(userId).get();
		user.setRouletted(user.isRouletted() ? false : true);
		userRepository.save(user);

		return user;
	}

}
