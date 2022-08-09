package com.nangman.api.service;

import com.nangman.api.dto.BoardDto;
import com.nangman.db.entity.*;
import com.nangman.db.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 *	방명록 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final UserRepository userRepository;

	private final BusRepository busRepository;

	private final BoardRepository boardRepository;

	@Override
	@Transactional
	public BoardDto.Info createBoard(BoardDto.createBoardRequest boardRequestInfo) {
		Board board = new Board();
		User user = userRepository.findByIdAndIsDeletedFalse(boardRequestInfo.getUserId()).get();
		Bus bus = busRepository.findReportById(boardRequestInfo.getBusId()).get();

		board.setUser(user);
		board.setBus(bus);
		board.setContent(boardRequestInfo.getContent());

		boardRepository.save(board);

		user.addBoard(board);
		bus.addBoard(board);

		userRepository.save(user);
		busRepository.save(bus);

		return new BoardDto.Info(boardRepository.findBoardById(boardRequestInfo.getBusId()).get());
	}

	@Override
	@Transactional(readOnly = true)
	public List<BoardDto.Info> getBoardsById(long busId) {
		List<Board> boardList = boardRepository.findBoardByBusId(busId);
		List<BoardDto.Info> boardDtos = new ArrayList<>();
		for (Board list: boardList) {
			boardDtos.add(new BoardDto.Info(list));
		}
		return boardDtos;
	}

	@Override
	@Transactional
	public void deleteBoard(long userId, long boardId) {
		boardRepository.delete(boardRepository.findBoardByIdAndUserId(boardId, userId).get());
	}
}
