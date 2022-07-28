package com.nangman.api.controller;

import com.nangman.api.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nangman.api.Service.UserService;
import com.nangman.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "유저 API", tags = {"user"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class UserController {
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	
	@PostMapping("user/")
	@ApiOperation(value = "회원 가입", notes = "이메일(필수), 패스워드(필수), 생일 데이터(선택)를 받아 회원가입 한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserDto.Info> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserDto.RegisterRequest registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		return new ResponseEntity<UserDto.Info>(new UserDto.Info(userService.createUser(registerInfo)), HttpStatus.OK);
	}
	
	@GetMapping("user/{userId}")
	@ApiOperation(value = "ID로 회원 정보 조회", notes = "ID를 기반으로 회원 정보를 응답한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserDto.Info> getUserByUserId(@PathVariable int userId) {
		// Id 기준으로 유저 정보 조회
		return new ResponseEntity<UserDto.Info>(new UserDto.Info(userService.getUserByUserId(userId)), HttpStatus.OK);
	}

	@GetMapping("user/logout")
	@ApiOperation(value = "로그아웃", notes = "로그아웃을 한다.	")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("user/login")
	@ApiOperation(value = "로그인", notes = "이메일과 패스워드를 통해 로그인 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserDto.class),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserDto.LoginRequest loginInfo, Model model, HttpSession session,
								   HttpServletResponse response) {
		String useremail = loginInfo.getUseremail();
		String password = loginInfo.getPassword();

		User user = userService.getUserByUseremail(useremail);

		log.info(user.getUseremail() + ", " + user.getPassword());
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		if (passwordEncoder.matches(password, user.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 어트리뷰트, 세션 반환 및 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)

			session.setAttribute("User", user);
			log.info(session.getId());
			log.info(user.getUseremail());
			log.info(user.getPassword());
			Cookie cookie = new Cookie("useremail", user.getUseremail());
			cookie.setPath("/"); // 도메인 기준 쿠키 생성
			cookie.setMaxAge(24*60*60*365); // 쿠키 만료기한 365일
			response.addCookie(cookie);

			return new ResponseEntity<UserDto.Info>(new UserDto.Info(user), HttpStatus.OK);
		}

		// *** 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.(controlAdvice 구현 필요)
		return new ResponseEntity<String>("아이디 또는 비밀번호를 확인해 주세요", HttpStatus.UNAUTHORIZED);
	}

	@DeleteMapping("user/{userId}")
	@ApiOperation(value = "회원 탈퇴", notes = "회원 정보의 is_deleted 필드를 true로 수정한다.(soft delete)")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserDto.Info> getUserByUserId(@PathVariable long userId) {
		return new ResponseEntity<UserDto.Info>(new UserDto.Info(userService.deleteuser(userId)), HttpStatus.OK);
	}

	@PutMapping("user")
	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보 중 생일 또는 비밀번호 수정한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<UserDto.Info> updateUserInfo(@RequestBody @ApiParam(value = "수정할 회원 정보", required = true) UserDto.RegisterRequest userInfo) {
		log.info(userInfo.getUseremail());
		return new ResponseEntity<UserDto.Info>(new UserDto.Info(userService.updateUser(userInfo)), HttpStatus.OK);
	}
}
