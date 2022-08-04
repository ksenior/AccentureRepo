package com.accenture.reto.comments_ms.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.accenture.reto.comments_ms.model.Comments;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CommentsService {
	@Value("${endpoint.mock}")
	private String uriExternalApi;
	
	private String path="/comments";
	
	public List<Comments> getAllComments(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriExternalApi+path);
		ResponseHandler <String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        try {
			String responseBody = httpClient.execute(httpGet, responseHandler);
			ObjectMapper objectMapper = new ObjectMapper();
			List<Comments> commentsList = objectMapper.readValue(responseBody, new TypeReference<List<Comments>>(){});
			return commentsList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<Comments> getCommentsByName(String name){
		List<Comments> comments = getAllComments();
		return comments.stream().filter(x -> x.getName().contains(name)).collect(Collectors.toList());
		
	}
	
	public List<Comments> getCommentsByIdUser(Long idUser){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriExternalApi+"/users/"+idUser+"/comments");
		ResponseHandler <String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return entity != null ? EntityUtils.toString(entity) : null;
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
        try {
			String responseBody = httpClient.execute(httpGet, responseHandler);
			ObjectMapper objectMapper = new ObjectMapper();
			List<Comments> commentsList = objectMapper.readValue(responseBody, new TypeReference<List<Comments>>(){});
			return commentsList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<Comments> getCommentsByNameAndIdUser(String name, Long idUser){
		List<Comments> comments = getCommentsByIdUser(idUser);
		return comments.stream().filter(x-> x.getName().contains(name)).collect(Collectors.toList());
	}
}
