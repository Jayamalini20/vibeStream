package com.vibeStream.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	String artist;
	String genre;
	String link;
	String image;
	boolean likedByCurrentUser;
	
	@OneToMany(mappedBy = "song")
    List<Likes> likes;

    @OneToMany(mappedBy = "song")
    List<Comments> comments;
    
	@ManyToMany
	List<Playlist> playlists;
	public Song() {
		super();
	}
	public Song(int id, String name, String artist, String genre, String link, List<Playlist> playlists, String image, boolean likedByCurrentUser) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.genre = genre;
		this.link = link;
		this.playlists = playlists;
		this.image = image;
		this.likedByCurrentUser = likedByCurrentUser;
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
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public boolean isLikedByCurrentUser() {
		return likedByCurrentUser;
	}
	public void setLikedByCurrentUser(boolean likedByCurrentUser) {
		this.likedByCurrentUser = likedByCurrentUser;
	}
	public List<Likes> getLikes() {
		return likes;
	}
	public void setLikes(List<Likes> likes) {
		this.likes = likes;
	}
	public List<Comments> getComments() {
		return comments;
	}
	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", artist=" + artist + ", genre=" + genre + ", link=" + link
				+ ", image=" + image + ", likedByCurrentUser=" + likedByCurrentUser + ", likes=" + likes + ", comments="
				+ comments + ", playlists=" + playlists + "]";
	}
	
	
	

}
