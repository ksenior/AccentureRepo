package com.accenture.reto.user_ms.service;

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

import com.accenture.reto.album_ms.model.Album;
import com.accenture.reto.user_ms.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserService {
	@Value("${endpoint.mock}")
	private String uriExternalApi;
	
	ObjectMapper mapper = new ObjectMapper();
	
	ResponseHandler <String> commonResponse = response -> {
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
    };
	
	public List<User> getAllUsers(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriExternalApi+"/users");
		ResponseHandler <String> responseHandler = commonResponse;
        try {
			String responseBody = httpClient.execute(httpGet, responseHandler);
			ObjectMapper objectMapper = new ObjectMapper();
			List<User> postList = objectMapper.readValue(responseBody, new TypeReference<List<User>>(){});
			return postList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public User getById(Long id){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriExternalApi+"/users/"+id);
		ResponseHandler <String> responseHandler = commonResponse;
        try {
			String responseBody = httpClient.execute(httpGet, responseHandler);
			ObjectMapper objectMapper = new ObjectMapper();
			User postList = objectMapper.readValue(responseBody, new TypeReference<User>(){});
			return postList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getUserByPermissionAndAlbum(List<Album> listAlbum){
		List<User> allUsers = getAllUsers();
		List<Long> allIdAlbum = listAlbum.stream().map(x-> x.getId()).collect(Collectors.toList());
		return allUsers.stream().filter(x -> allIdAlbum.contains(x.getId())).collect(Collectors.toList());
	}

}
