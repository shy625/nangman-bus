package com.nangman.api.controller;

import com.nangman.api.dto.BoardDto;
import com.nangman.api.service.BoardService;
import com.nangman.db.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * 방명록 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Slf4j
@Api(value = "방명록 API", tags = {"Board"})
@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	@PostMapping("board")
	@ApiOperation(value = "방명록 생성", notes = "방명록을 생성해 줍니다.")
    @ApiResponses({
        @ApiResponse(code = 201, message = "방명록 작성 성공"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<BoardDto.Info> createBoard(
			@RequestBody @ApiParam(value="방명록 정보", required = true) @Valid BoardDto.createBoardRequest createInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		return new ResponseEntity<BoardDto.Info>(boardService.createBoard(createInfo), HttpStatus.CREATED);
	}

	@GetMapping("board/{busId}")
	@ApiOperation(value = "busID로 방명록 정보 조회", notes = "busID를 기반으로 방명록 리스트 정보를 응답한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<List<BoardDto.Info>> getBoardsById(@PathVariable long busId) {
		// Id 기준으로 유저 정보 조회
		return new ResponseEntity<List<BoardDto.Info>>(boardService.getBoardsById(busId), HttpStatus.OK);
	}

	@DeleteMapping("board/{boardId}")
	@ApiOperation(value = "회원 탈퇴", notes = "회원 정보의 is_deleted 필드를 true로 수정한다.(soft delete)")
	@ApiResponses({
			@ApiResponse(code = 200, message = "성공"),
			@ApiResponse(code = 404, message = "사용자 없음"),
			@ApiResponse(code = 500, message = "서버 오류")
	})
	public ResponseEntity<String> deleteUser(@PathVariable long boardId, HttpSession session) {
		User user = (User) session.getAttribute("User");

		boardService.deleteBoard(user.getId(), boardId);

		return new ResponseEntity<String>("삭제 완료", HttpStatus.OK);
	}
}
