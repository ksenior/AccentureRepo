package com.accenture.reto.user_ms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Geo {

	@JsonIgnore
	private Long id;
	

	private String lat;

	private String lng;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}
	
	
}
