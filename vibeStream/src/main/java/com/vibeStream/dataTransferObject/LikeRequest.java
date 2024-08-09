package com.vibeStream.dataTransferObject;


import com.vibeStream.entities.Song;
import com.vibeStream.entities.Users;

public class LikeRequest {
    private Users user;
    private Song song;

    public LikeRequest() {
    }

    public LikeRequest(Users user, Song song) {
        this.user = user;
        this.song = song;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }
}
