package com.accenture.reto.album_ms.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.reto.album_ms.controller.AlbumUserPermissionController;
import com.accenture.reto.album_ms.model.AlbumUser;
import com.accenture.reto.album_ms.repository.AlbumUserRepository;

@Service
public class AlbumUserService {

	@Autowired
	private AlbumUserRepository albumUserRepo;
	
	@Autowired
	private AlbumUserPermissionController albumUserPermissionController;
	
	
	
	public List<AlbumUser> findAlbumPermissionByIdUser(Long idUser){
		return albumUserRepo.findByIdUser(idUser);
	}
	
	public AlbumUser saveAlbumUserPermission(AlbumUser albumUser) {
			try {
				return albumUserRepo.save(albumUser);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;

	}
	
	public boolean updateAlbumUserPermission(AlbumUser albumUser) {
		Optional<AlbumUser> result = albumUserRepo.findById(albumUser.getId());
		if(result.isPresent()) {
			albumUserPermissionController.deleteUserPermission(albumUser.getId());
			result.get().setAlbumUserPermission(albumUser.getAlbumUserPermission());
			albumUserRepo.save(result.get());
			return true;
		}
		return false;
	}
	
	@Transactional
	public boolean deleteAlbumUserPermission(AlbumUser albumUser) {
		Optional<AlbumUser> result = albumUserRepo.findById(albumUser.getId());
		if(result.isPresent()) {
			albumUserPermissionController.deleteUserPermission(albumUser.getId());
			albumUserRepo.delete(albumUser);
			return true;
		}
		return false;
	}
}
