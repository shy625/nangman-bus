package com.nangman.db.repository;

import com.nangman.db.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

    Optional<Board> findBoardByBusId(long id);

    List<Board> findBoardByBusIdOrderByCreatedDate(long busId);

    Optional<Board> findBoardByIdAndUserId(long boardId, Long id);
}