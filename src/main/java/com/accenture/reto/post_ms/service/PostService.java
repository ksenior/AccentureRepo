package com.accenture.reto.post_ms.service;

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

import com.accenture.reto.post_ms.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PostService {
	@Value("${endpoint.mock}")
	private String uriExternalApi;
	
	public List<Post> getAllPost(){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uriExternalApi+"/posts");
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
			List<Post> postList = objectMapper.readValue(responseBody, new TypeReference<List<Post>>(){});
			return postList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
