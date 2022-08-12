package com.nangman.db.repository;

import com.nangman.db.entity.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, Long> {
    List<UserReport> findUserReportsByUserIdOrderByCreatedDateDesc(Long userId);
}