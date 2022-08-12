package com.nangman.db.repository;

import com.nangman.db.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusStopRepository extends JpaRepository<BusStop, Long> {
    List<BusStop> findBusStopByRouteId(long routeId);
    void deleteBusStopByRouteId(long routeId);
}
