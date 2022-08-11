package com.nangman.db.repository;

import com.nangman.db.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long>{
    Optional<Bus> findReportById(long id);
}