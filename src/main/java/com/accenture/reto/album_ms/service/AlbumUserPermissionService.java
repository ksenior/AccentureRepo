package com.accenture.reto.album_ms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.reto.album_ms.repository.AlbumUserPermissionRepository;

@Service
public class AlbumUserPermissionService {
	
	@Autowired
	private AlbumUserPermissionRepository albumUserRepo;

	@Transactional
	public void deleteAlbumUserPermission(Long id) {
			albumUserRepo.deleteUsersByIdAlbumUser(id);
	}
	
	public List<Long> findAlbumUserPermisionByIdPermission(Long id){
		return albumUserRepo.findAlbumUserPermisionByIdPermission(id);
	}

}
