package com.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tunehub.entities.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer>{

	Object findByName(String name);
}
