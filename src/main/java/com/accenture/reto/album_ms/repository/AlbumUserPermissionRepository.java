package com.accenture.reto.album_ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.accenture.reto.album_ms.model.AlbumUserPermission;


public interface AlbumUserPermissionRepository extends JpaRepository<AlbumUserPermission, Long> {
	@Modifying
    @Query("delete from AlbumUserPermission u where u.albumUser.id = ?1")
    public void deleteUsersByIdAlbumUser(Long id);
	
	@Modifying
    @Query("select u.albumUser.idUser from AlbumUserPermission u where u.permission.id = ?1")
    public List<Long> findAlbumUserPermisionByIdPermission(Long id);
}
