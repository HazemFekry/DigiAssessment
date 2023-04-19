package com.assessment.permission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission,Long> {

    Optional<Permission> findByUserEmail(String userEmail);
}
