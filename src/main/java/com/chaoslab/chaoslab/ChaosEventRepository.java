package com.chaoslab.chaoslab;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChaosEventRepository
        extends JpaRepository<ChaosEvent, Long> {

            List<ChaosEvent> findByType(String type);

            List<ChaosEvent> findTop10ByOrderByIdDesc();
}
