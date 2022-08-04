package com.accenture.reto.photo_ms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.reto.photo_ms.model.Photo;
import com.accenture.reto.photo_ms.service.PhotoService;
import com.accenture.reto.response.ResponseHandler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/photo")
public class PhotoController {
	
	private ResponseEntity<Object> resultResponse;
	
	@Autowired
	private PhotoService photoService;

	@ApiOperation(value = "Get All Photo", notes = "Returns All Photo Info")
	@GetMapping
	public ResponseEntity<Object> getAllPhoto(@RequestParam(required = false) Long userId){
		List<Photo> result = new ArrayList<>();
		try {
			if(userId != null) {
				result = photoService.getPhotosByUserId(userId);
			}
			else {
				result = photoService.getAllPhotos();
			}
			resultResponse =  ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			resultResponse =  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
	
	@ApiOperation(value = "Get All Photo by UserId", notes = "Returns All Photo for one UserId")
	@GetMapping("/filtered")
	public ResponseEntity<Object> getAllPhotoByUserId(@RequestParam Long userId){
		List<Photo> result = new ArrayList<>();
		try {
			result = photoService.getPhotosByUserId(userId);
			resultResponse =  ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			resultResponse =  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
}
