package com.accenture.reto.album_ms.controller;

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
import com.accenture.reto.response.ResponseHandler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/album")
public class AlbumController {

private ResponseEntity<Object> resultResponse;
	
	@Autowired
	private AlbumService albumService;

	@ApiOperation(value = "Get All album", notes = "Returns all albums, it was created for know the data of albumes to be shared")
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllAlbum(){
		try {
			List<Album> result = albumService.getAllAlbum();
			resultResponse =  ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			e.printStackTrace();
			resultResponse =  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	
	@ApiOperation(value = "Get album by idAlbum", notes = "Returns album where your id is equals that the parameter value, the userId is required for validate the permission on album ")
	@GetMapping("/{idAlbum}")
	public ResponseEntity<Object> getAlbumById(@PathVariable Long idAlbum, @RequestParam Long userId ){
		try {
			Album result = albumService.getAlbumById(idAlbum);
			if(result.getUserId()==userId || albumService.ValidatePermissionOnAlbum(idAlbum,userId)) {
				resultResponse =  ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
			}
			else {
				resultResponse =  ResponseHandler.generateResponse("Id Album no existe o no tiene permiso para visualizarlo", HttpStatus.NOT_FOUND, null);
			}

		} catch (Exception e) {
			resultResponse =  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	
	@ApiOperation(value = "Get album by UserId", notes = "Returns album where it was created by userId parameter and shared with him")
	@GetMapping("/byUserId")
	public ResponseEntity<Object> getAlbumByUserId(@RequestParam Long userId ){
		try {
			List<Album> result = albumService.getAlbumByUserId(userId);
			result = albumService.addAlbumShared(result,userId);
			resultResponse =  ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			resultResponse =  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	
	

}
