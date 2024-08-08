package com.vibeStream.services;

import java.util.List;

import com.vibeStream.entities.Playlist;

public interface PlaylistService {

	void addPlaylist(Playlist playlist);

	List<Playlist> fetchAllPlaylists();

	Playlist findPlaylistById(int id);

}
