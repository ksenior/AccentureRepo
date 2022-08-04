package com.accenture.reto.album_ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.reto.album_ms.service.AlbumUserPermissionService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/album/user_permission")
public class AlbumUserPermissionController {
	
	@Autowired
	private AlbumUserPermissionService albumUserService;

	@ApiOperation(value = "Delete User Permission", notes = "Your function is to delete the permission on the album over one user")
	public boolean deleteUserPermission(Long idAlbumUser) {
		albumUserService.deleteAlbumUserPermission(idAlbumUser);
		return true;
	}
}
