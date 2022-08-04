package com.accenture.reto.album_ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.reto.album_ms.model.AlbumUser;
import com.accenture.reto.album_ms.service.AlbumUserService;
import com.accenture.reto.response.ResponseHandler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/album/permission")
public class AlbumUserController {
	
	private ResponseEntity<Object> resultResponse;

	@Autowired
	private AlbumUserService albumUserService;

	@ApiOperation(value = "Post save Album User Permission", notes = "Your function is to save the permission on the album over one user.")
	@PostMapping
	public ResponseEntity<Object> saveAlbumUserPermission(@RequestBody AlbumUser albumUser){
		try {
			albumUserService.saveAlbumUserPermission(albumUser);
			resultResponse = ResponseHandler.generateResponse("Permiso Registrado Exitosamente", HttpStatus.OK, null);
		} catch (DataIntegrityViolationException e) {
			resultResponse = ResponseHandler.generateResponse("Permiso ya se encuentra registrado", HttpStatus.BAD_REQUEST, null);
			
		} catch (Exception e) {
			resultResponse = ResponseHandler.generateResponse("Error técnico. Cod:CL002", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	
	@ApiOperation(value = "Put update Album User Permission", notes = "Your function is to update the permission on the album over one user")
	@PutMapping
	public ResponseEntity<Object> updateAlbumUserPermission(@RequestBody AlbumUser albumUser){
		try {
			if(albumUserService.updateAlbumUserPermission(albumUser)) {
				resultResponse = ResponseHandler.generateResponse("Permiso Actualizado Exitosamente", HttpStatus.OK, null);
				}
			else {
				resultResponse = ResponseHandler.generateResponse("Permiso No se encuentra registrado", HttpStatus.BAD_REQUEST, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultResponse = ResponseHandler.generateResponse("Error técnico. Cod:CL002", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	
	@ApiOperation(value = "Delete Album User Permission", notes = "Your function is to delete the permission on the album over one user")
	@DeleteMapping
	public ResponseEntity<Object> deleteAlbumUserPermission(@RequestBody AlbumUser albumUser){
		try {
			if(albumUserService.deleteAlbumUserPermission(albumUser))
			resultResponse = ResponseHandler.generateResponse("Permiso Eliminado Exitosamente", HttpStatus.OK, null);
			else
			resultResponse = ResponseHandler.generateResponse("Permiso no Existe", HttpStatus.NOT_FOUND, null);
		} catch (Exception e) {
			e.printStackTrace();
			resultResponse = ResponseHandler.generateResponse("Error técnico. Cod:CL002", HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	
}
