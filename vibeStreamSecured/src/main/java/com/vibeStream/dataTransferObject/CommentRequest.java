package com.vibeStream.dataTransferObject;


import com.vibeStream.entities.Song;
import com.vibeStream.entities.Users;

public class CommentRequest {
    private Users user;
    private Song song;
    private String commentText;

    public CommentRequest() {
    }

    public CommentRequest(Users user, Song song, String commentText) {
        this.user = user;
        this.song = song;
        this.commentText = commentText;
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
