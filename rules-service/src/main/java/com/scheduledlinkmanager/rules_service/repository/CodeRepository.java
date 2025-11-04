package com.scheduledlinkmanager.rules_service.repository;

import com.scheduledlinkmanager.rules_service.model.Code;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CodeRepository extends JpaRepository<Code, UUID> {
    @Query(value = "SELECT * FROM code OFFSET FLOOR(RANDOM() * (SELECT COUNT(*) FROM code)) LIMIT :count", nativeQuery = true)
    List<Code> findRandomCodesEfficient(@Param("count") int count);
}