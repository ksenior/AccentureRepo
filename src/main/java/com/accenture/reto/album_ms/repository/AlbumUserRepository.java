package com.accenture.reto.album_ms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accenture.reto.album_ms.model.AlbumUser;

public interface AlbumUserRepository extends JpaRepository<AlbumUser, Long>{
	
	AlbumUser findByIdUserAndIdAlbum(Long idUser, Long idAlbum);
	
	List<AlbumUser> findByIdUser(Long idUser);

}
