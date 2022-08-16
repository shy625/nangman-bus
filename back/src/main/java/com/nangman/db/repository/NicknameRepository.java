package com.nangman.db.repository;

import com.nangman.db.entity.Nickname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NicknameRepository extends JpaRepository<Nickname, Long> {

}
