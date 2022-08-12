package com.nangman.api.service;

import com.nangman.api.dto.BoardDto;
import com.nangman.db.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
@Service
public interface BoardService {
	@Transactional
	List<BoardDto.Info> createBoard(BoardDto.createBoardRequest userRegisterInfo);

	@Transactional(readOnly = true)
	List<BoardDto.Info> getBoardsById(long busId);

	@Transactional
	void deleteBoard(long boardId, long userId);
}
