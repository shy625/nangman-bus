package com.nangman.db.repository;

import com.nangman.db.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    Optional<Route> findRouteByCode(String code);
    void deleteRouteByCode(String code);
}
