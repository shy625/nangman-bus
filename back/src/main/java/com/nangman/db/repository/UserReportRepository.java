package com.nangman.db.repository;

import com.nangman.db.entity.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, Long> {
    public List<UserReport> findUserReportsByUserId(Long userId);
}