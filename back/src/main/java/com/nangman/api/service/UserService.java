package com.nangman.api.service;

import com.nangman.api.dto.UserDto;
import com.nangman.db.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
@Service
public interface UserService {
	@Transactional
	User createUser(UserDto.RegisterRequest userRegisterInfo);

	@Transactional(readOnly = true)
	User getUserByUserId(long userId);

	@Transactional(readOnly = true)
	User getUserByUseremail(String useremail);

	@Transactional
	User deleteUser(long userId);

	@Transactional
	User updateUser(UserDto.RegisterRequest userInfo);

	User updateUserIsRouletted(long userId);

	void updateUserNickname();
}
