	package com.accenture.reto.post_ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.reto.post_ms.model.Post;
import com.accenture.reto.post_ms.service.PostService;
import com.accenture.reto.response.ResponseHandler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/post")
public class PostController {

	private ResponseEntity<Object> resultResponse;
	
	@Autowired
	private PostService postService;
	
	@ApiOperation(value = "Get All Post", notes = "Returns All Post Info")
	@GetMapping
	public ResponseEntity<Object> getAllPost(){
		try {
			List<Post> result = postService.getAllPost();
			resultResponse =  ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			resultResponse =  ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
}
