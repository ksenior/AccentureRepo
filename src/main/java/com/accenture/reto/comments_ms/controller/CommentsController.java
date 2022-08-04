package com.accenture.reto.comments_ms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.reto.comments_ms.model.Comments;
import com.accenture.reto.comments_ms.service.CommentsService;
import com.accenture.reto.response.ResponseHandler;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/comments")
public class CommentsController {

	private ResponseEntity<Object> resultResponse;

	@Autowired
	private CommentsService commentsService;

	@ApiOperation(value = "Get All Comments", notes = "Returns All Comments Info")
	@GetMapping
	public ResponseEntity<Object> getAllComments() {
		try {
			List<Comments> result = commentsService.getAllComments();
			resultResponse = ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			resultResponse = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}

	@ApiOperation(value = "Get All Comments", notes = "Returns All Comments Info filtering by name or idUser, or both")
	@GetMapping("/filtered")
	public ResponseEntity<Object> getAllComments(@RequestParam(required = false) String name,
			@RequestParam(required = false) Long idUser) {
		try {
			List<Comments> result = new ArrayList<>();
			if (name != null && idUser > 0) {
				result = commentsService.getCommentsByNameAndIdUser(name, idUser);
			} else if (name != null) {
				result = commentsService.getCommentsByName(name);
			} else if (idUser != null) {
				result = commentsService.getCommentsByIdUser(idUser);
			} else {
				return resultResponse = ResponseHandler.generateResponse(ResponseHandler.VALIDATION,
						HttpStatus.BAD_REQUEST, null);
			}
			resultResponse = ResponseHandler.generateResponse(ResponseHandler.SUCCESS, HttpStatus.OK, result);
		} catch (Exception e) {
			resultResponse = ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		return resultResponse;
	}
}
