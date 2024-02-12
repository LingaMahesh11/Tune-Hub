package com.tunehub.services;

import java.util.List;

import com.tunehub.entities.Song;
import com.tunehub.entities.Users;

public interface SongService {

	public void addSong(Song song);

	public List<Song> fetchAllSongs();
	
	public boolean songExists(String name);

	public void updateSong(Song s);
	
	public Song getSongById(int id);
	
	public void removeFavoriteSong(Users user, Song song);
}
