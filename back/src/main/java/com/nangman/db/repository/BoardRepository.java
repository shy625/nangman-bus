package com.nangman.db.repository;

import com.nangman.db.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

    Optional<Board> findBoardById(long id);

    List<Board> findBoardByBusId(long busId);

    Optional<Board> findBoardByIdAndUserId(long boardId, Long id);

    List<Board> findBoardByBusIdOrderByCreatedDateDesc(long busId);
}