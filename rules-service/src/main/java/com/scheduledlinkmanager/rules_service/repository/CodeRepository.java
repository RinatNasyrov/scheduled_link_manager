package com.scheduledlinkmanager.rules_service.repository;

import com.scheduledlinkmanager.rules_service.model.Code;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, UUID> {

}