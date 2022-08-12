package com.nangman.db.repository;

import com.nangman.db.entity.Busstop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusstopRepository extends JpaRepository<Busstop, Long> {
    List<Busstop> findBusstopByCode(String code);
    void deleteBusstopByCode(String code);
}
