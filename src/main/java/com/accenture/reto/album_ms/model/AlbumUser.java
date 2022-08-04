package com.accenture.reto.album_ms.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "album_user",indexes = {@Index(columnList = "album_id,user_id", unique = true)})
public class AlbumUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@ApiModelProperty(value = "Is the consecutive used for update/delete permission")
	private Long id;
	
	@Column(name = "user_id")
	private Long idUser;
	
	@Column(name = "album_id")
	private Long idAlbum;
	
	@OneToMany(mappedBy = "albumUser", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<AlbumUserPermission> albumUserPermission;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}

	public Set<AlbumUserPermission> getAlbumUserPermission() {
		return albumUserPermission;
	}

	public void setAlbumUserPermission(Set<AlbumUserPermission> albumUserPermission) {
		albumUserPermission.forEach(item -> {
			item.setAlbumUser(this);
		});
		this.albumUserPermission = albumUserPermission;
	}

	
	
}
