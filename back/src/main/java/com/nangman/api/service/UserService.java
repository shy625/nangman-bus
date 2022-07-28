package com.nangman.api.service;

import com.nangman.api.dto.UserDto;
import com.nangman.db.entity.User;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserDto userRegisterInfo);
	User getUserByUseremail(String useremail);
}
