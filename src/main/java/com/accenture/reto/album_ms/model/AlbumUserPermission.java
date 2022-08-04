package com.accenture.reto.album_ms.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "album_user_permission",indexes = {@Index(columnList = "id_album_user,id_permission", unique = true)})
public class AlbumUserPermission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(targetEntity = AlbumUser.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_album_user", referencedColumnName = "id")
	private AlbumUser albumUser;
	
	@ManyToOne(targetEntity = Permission.class)
	@JoinColumn(name = "id_permission", referencedColumnName = "id")
	private Permission permission;
	
	@Column(name = "record_date", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date record_date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 

	public AlbumUser getAlbumUser() {
		return albumUser;
	}

	public void setAlbumUser(AlbumUser albumUser) {
		this.albumUser = albumUser;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Date getRecord_date() {
		return record_date;
	}

	public void setRecord_date(Date record_date) {
		this.record_date = record_date;
	}

	
	
}
