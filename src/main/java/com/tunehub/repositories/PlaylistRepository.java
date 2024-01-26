package com.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tunehub.entities.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer>{

	
}
