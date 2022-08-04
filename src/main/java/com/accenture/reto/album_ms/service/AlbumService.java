package com.accenture.reto.album_ms.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.accenture.reto.album_ms.model.Album;
import com.accenture.reto.album_ms.model.AlbumUser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AlbumService {
	@Value("${endpoint.mock}")
	private String uriExternalApi;
	
	@Autowired
	private AlbumUserService albumUserService;
	
	
	private final String path = "/albums";
	
	public List<Album> getAllAlbum(){
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
			List<Album> albumList = objectMapper.readValue(responseBody, new TypeReference<List<Album>>(){});
			return albumList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Album getAlbumById(Long id){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriExternalApi+path+"/"+id);
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
			Album albumList = objectMapper.readValue(responseBody, new TypeReference<Album>(){});
			return albumList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List <Album> getAlbumByUserId(Long id){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriExternalApi+"/users"+"/"+id+"/albums");
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
			List <Album> albumList = objectMapper.readValue(responseBody, new TypeReference<List <Album>>(){});
			return albumList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public List<Album> getAllAlbumFileteredByPermission(List<Long> listIdAlbum){
		List<Album> result = this.getAllAlbum();
		return result.stream().filter(x -> listIdAlbum.contains(x.getId())).collect(Collectors.toList());
	}
	
	public List<Album> getAllAlbumFiletered(List<Album> listIdAlbum, Long userId){
		List<Album> result = getAlbumByUserId(userId);
		List<AlbumUser> listAlbumUser = albumUserService.findAlbumPermissionByIdUser(userId);
		List<Long> listIdAlbumUser = listAlbumUser.stream().map(x -> x.getIdAlbum()).collect(Collectors.toList());
		List<Album> listAlbumToAdd = listIdAlbum.stream().filter(x-> listIdAlbumUser.contains(x.getId())).collect(Collectors.toList());
		List<Album> combinedList = Stream.of(result, listAlbumToAdd)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
		return  combinedList;
	}
	
	public List<Album> addAlbumShared(List<Album> listIdAlbum, Long userId){
		List<Album> result = getAllAlbum();
		List<AlbumUser> listAlbumUser = albumUserService.findAlbumPermissionByIdUser(userId);
		List<Long> listIdAlbumUser = listAlbumUser.stream().map(x -> x.getIdAlbum()).collect(Collectors.toList());
		List<Album> listAlbumToAdd = result.stream().filter(x-> listIdAlbumUser.contains(x.getId())).collect(Collectors.toList());
		List<Album> combinedList = Stream.of(listIdAlbum, listAlbumToAdd)
                .flatMap(x -> x.stream())
                .collect(Collectors.toList());
		return  combinedList;
	}
	
	public boolean ValidatePermissionOnAlbum(Long idAlbum,Long userId) {
		List<AlbumUser> listAlbumUser = albumUserService.findAlbumPermissionByIdUser(userId);
		return listAlbumUser.stream().anyMatch(x-> x.getIdAlbum() == idAlbum);
	}
	
}
