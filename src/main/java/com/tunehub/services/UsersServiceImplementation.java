package com.tunehub.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tunehub.entities.Users;
import com.tunehub.repositories.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class UsersServiceImplementation implements UsersService {

	@Autowired
	UsersRepository repo;
	
	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "User added successfully";
	}

	@Override
	public boolean emailExists(String email) {
		if(repo.findByEmail(email)==null) {
			return false;
		}
		else {
			return true;
		}
		
	}

	@Override
	public boolean validateUser(String email, String password) {
		Users user = (Users) repo.findByEmail(email);
		
		String db_pass = user.getPassword();
		if(password.equals(db_pass)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getRole(String email) {
		Users user = (Users) repo.findByEmail(email);
		return user.getRole();
	}

	@Override
	public Users getUser(String email) {
		return (Users) repo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		repo.save(user);
	}

	@Override
	public String getUsername(String email) {
		Users user = (Users) repo.findByEmail(email);	
		return user.getUsername();
	}

	@Transactional
    public void removeFavoriteSong(int userId, int songId) {
        Optional<Users> optionalUser = repo.findById(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.getFavoriteSongs().removeIf(song -> song.getId() == songId);
            repo.save(user);
        } else {
            // Handle case where user with given ID is not found
            // You may throw an exception, log an error, or handle it differently based on your requirement
        }
	}

}
