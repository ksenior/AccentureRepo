package com.accenture.reto.user_ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.reto.album_ms.model.Album;
import com.accenture.reto.album_ms.service.AlbumService;
import com.accenture.reto.album_ms.service.AlbumUserPermissionService;
import com.accenture.reto.response.ResponseHandler;
import com.accenture.reto.user_ms.model.User;
import com.accenture.reto.user_ms.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	
	private ResponseEntity<Object> resultResponse;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AlbumUserPermissionService albumUserPermissionService;
	
	@Autowired
	private AlbumService albumService;

	@ApiOperation(value = "Get All Users", notes = "Returns All Users Info")
	@GetMapping
	public ResponseEntity<Object> getAllUsers(){
		try {
			List<User> result = userService.getAllUsers();
			resultResponse =  ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			resultResponse =  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	
	@ApiOperation(value = "Get User By Id", notes = "Returns User Info for one Id")
	@GetMapping("/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable Long id ){
		try {
			User result = userService.getById(id);
			resultResponse =  ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			resultResponse =  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	
	@ApiOperation(value = "Get User By Id", notes = "Returns User Info for one Id and idpermission ")
	@GetMapping("/byPermission")
	public ResponseEntity<Object> getUserByPermissionAndAlbum(@RequestParam (name = "idAlbum", required = true) Long idAlbum, 
															@RequestParam (name = "idPermission", required = true) Long idPermission)
	{
		List<Long> resultPermission = albumUserPermissionService.findAlbumUserPermisionByIdPermission(idPermission);
		List<Album> resultIdUsers = albumService.getAllAlbumFileteredByPermission(resultPermission);
		try {
			List<User> result = userService.getUserByPermissionAndAlbum(resultIdUsers);
			resultResponse =  ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			resultResponse =  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	

}
