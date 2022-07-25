package com.nangman.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nangman.api.request.UserLoginPostReq;
import com.nangman.api.response.UserLoginPostRes;
import com.nangman.api.service.UserService;
import com.nangman.common.model.response.BaseResponseBody;
import com.nangman.common.util.JwtTokenUtil;
import com.nangman.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 인증 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;



    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserLoginPostReq loginInfo, Model model, HttpSession session,
                                                  HttpServletResponse response) {
        String userId = loginInfo.getId();
        String password = loginInfo.getPassword();

        User user = userService.getUserByUserId(userId);
        log.info(user.getUseremail() + ", " + user.getPassword());
        // 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
        if (passwordEncoder.matches(password, user.getPassword())) {
            // 유효한 패스워드가 맞는 경우, 어트리뷰트, 세션 반환 및 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)

            session.setAttribute("loginUser", user);
            log.info(session.getId());
            log.info(user.getUseremail());
            log.info(user.getPassword());
            Cookie cookie = new Cookie("id", user.getUseremail());
            cookie.setPath("/");
            cookie.setMaxAge(200);
            response.addCookie(cookie);
            return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(userId)));
        }

        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
        return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "아이디 또는 비밀번호 확인 후 다시 로그인하세요!", null));
    }
}
