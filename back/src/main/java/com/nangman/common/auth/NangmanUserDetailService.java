package com.nangman.common.auth;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nangman.api.service.UserService;
import com.nangman.db.entity.User;


/**
 * 현재 액세스 토큰으로 부터 인증된 유저의 상세정보(활성화 여부, 만료, 롤 등) 관련 서비스 정의.
 */
@Component
public class NangmanUserDetailService implements UserDetailsService{
	private UserService userService;

	public NangmanUserDetailService(@Lazy UserService userService) {
		this.userService = userService;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		User user = userService.getUserByUserId(username);
    		if(user != null) {
    			NangmanUserDetails userDetails = new NangmanUserDetails(user);
    			return userDetails;
    		}
    		return null;
    }
}
