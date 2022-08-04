package com.accenture.reto.album_ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.reto.album_ms.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}
