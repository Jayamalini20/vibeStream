package com.vibeStream.entities;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
@Entity
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	String image;
	String createdBy;
	String visibility;
	@ManyToMany
	List<Song> songs;
	public Playlist() {
		super();
	}
	public Playlist(int id, String name, String image, String createdBy, String visibility, List<Song> songs) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.createdBy = createdBy;
		this.visibility = visibility;
		this.songs = songs;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Song> getSongs() {
		return songs;
	}
	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", image=" + image + ", createdBy=" + createdBy
				+ ", visibility=" + visibility + "]";
	}
	
}

