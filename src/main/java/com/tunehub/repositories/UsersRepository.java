package com.tunehub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tunehub.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{

	Object findByEmail(String email);

	Optional<Users> findById(int id);

	
}
