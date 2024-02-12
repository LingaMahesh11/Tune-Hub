package com.tunehub.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Song {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name;
	String artist;
	String gener;
	String link;
	
	@ManyToMany
	List<Playlist> playlists;
	
	@ManyToMany(mappedBy = "favoriteSongs")
    private List<Users> favoritedByUsers;

	public Song() {
		super();
	}

	public Song(int id, String name, String artist, String gener, String link, List<Playlist> playlists,
			List<Users> favoritedByUsers) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.gener = gener;
		this.link = link;
		this.playlists = playlists;
		this.favoritedByUsers = favoritedByUsers;
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

	public String getGener() {
		return gener;
	}

	public void setGener(String gener) {
		this.gener = gener;
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

	public List<Users> getFavoritedByUsers() {
		return favoritedByUsers;
	}

	public void setFavoritedByUsers(List<Users> favoritedByUsers) {
		this.favoritedByUsers = favoritedByUsers;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", artist=" + artist + ", gener=" + gener + ", link=" + link
				+ ", playlists=" + playlists + ", favoritedByUsers=" + favoritedByUsers + "]";
	}

	
	
	
}
