package com.tunehub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Song;
import com.tunehub.entities.Users;
import com.tunehub.repositories.SongRepository;
import com.tunehub.repositories.UsersRepository;

@Service
public class SongServiceImplementation implements SongService {

	@Autowired
	private SongRepository repo;
	private UsersRepository userRepo;
	
	@Override
	public void addSong(Song song) {
		repo.save(song);
	}

	@Override
	public List<Song> fetchAllSongs() {
		return repo.findAll();
	}

	@Override
	public boolean songExists(String name) {
		if(repo.findByName(name)==null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void updateSong(Song s) {
		repo.save(s);
	}

	@Override
	public Song getSongById(int id) {
		Optional<Song> songOptional = repo.findById(id);
        return songOptional.orElse(null);
	}

	@Override
	public void removeFavoriteSong(Users user, Song song) {
		user.getFavoriteSongs().remove(song);
        userRepo.save(user);
	}


}
