package com.nangman.api.controller;

import com.nangman.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nangman.api.service.UserService;
import com.nangman.common.auth.NangmanUserDetails;
import com.nangman.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "유저 API", tags = {"user"})
@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	
	@PostMapping("/register")
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserDto> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) UserDto registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		User user = userService.createUser(registerInfo);
		UserDto userInfo = new UserDto(user.getUseremail(), user.getUserBirthday());

		return new ResponseEntity<UserDto>(userInfo, HttpStatus.OK);
	}
	
	@GetMapping("/info")
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 401, message = "인증 실패"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserDto> getUserInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		NangmanUserDetails userDetails = (NangmanUserDetails)authentication.getDetails();
		String useremail = userDetails.getUser().getUseremail();
		User user = userService.getUserByUseremail(useremail);
		UserDto userInfo = new UserDto(user.getUseremail(), user.getUserBirthday());

		return new ResponseEntity<UserDto>(userInfo, HttpStatus.OK);
	}

	@GetMapping("/logout")
	@ApiOperation(value = "로그아웃", notes = "로그아웃을 한다.	")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "<strong>아이디(useremail)와 패스워드</strong>를 통해 로그인 한다.")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공", response = UserDto.class),
			@ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<?> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserDto loginInfo, Model model, HttpSession session,
								   HttpServletResponse response) {
		String useremail = loginInfo.getUseremail();
		String password = loginInfo.getPassword();

		User user = userService.getUserByUseremail(useremail);

		log.info(user.getUseremail() + ", " + user.getPassword());
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		if (passwordEncoder.matches(password, user.getPassword())) {
			// 유효한 패스워드가 맞는 경우, 어트리뷰트, 세션 반환 및 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)

			session.setAttribute("loginUser", user);
			log.info(session.getId());
			log.info(user.getUseremail());
			log.info(user.getPassword());
			Cookie cookie = new Cookie("useremail", user.getUseremail());
			cookie.setPath("/"); // 도메인 기준 쿠키 생성
			cookie.setMaxAge(24*60*60*365);
			response.addCookie(cookie);

			UserDto userInfo = new UserDto(user.getUseremail(), user.getUserBirthday());

			return new ResponseEntity<UserDto>(userInfo, HttpStatus.OK);
		}

		// *** 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.(controlAdvice 구현 필요)
		return new ResponseEntity<String>("아이디 또는 비밀번호를 확인해 주세요", HttpStatus.UNAUTHORIZED);
	}
}
