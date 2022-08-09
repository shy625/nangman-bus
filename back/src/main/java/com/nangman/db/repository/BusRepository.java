package com.nangman.db.repository;

import com.nangman.db.entity.Bus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusRepository {
    Optional<Bus> findBusByLicense(String licenseNo);
}
