package com.accenture.reto.photo_ms.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.accenture.reto.photo_ms.model.Photo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PhotoService {
	@Value("${endpoint.mock}")
	private String uriExternalApi;
	
	public List<Photo> getAllPhotos(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriExternalApi+"/photos");
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
			List<Photo> photoList = objectMapper.readValue(responseBody, new TypeReference<List<Photo>>(){});
			return photoList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public List<Photo> getPhotosByUserId(Long userId){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriExternalApi+"/users/"+userId+"/photos");
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
			List<Photo> photoList = objectMapper.readValue(responseBody, new TypeReference<List<Photo>>(){});
			return photoList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
