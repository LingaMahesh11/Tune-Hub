package com.tunehub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tunehub.entities.Song;
import com.tunehub.entities.Users;
import com.tunehub.services.SongService;
import com.tunehub.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SongController {

	@Autowired
	private SongService service;
	
	@Autowired
	public UsersService userService;
	
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatus  = service.songExists(song.getName());
		if(songStatus==false) {
			service.addSong(song);
			System.out.println("Song added successfully");
		}
		else {
			System.out.println("Song already exists");
		}
		return "adminhome";
	}
	
	@GetMapping("/viewSongs")
	public String viewSongs(Model model) {
		List<Song> songsList = service.fetchAllSongs();
		
		model.addAttribute("songs",songsList);
		
		return "displaySongs";
	}
	
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		boolean premiumUser = true;
		
		if(premiumUser == true) {
			List<Song> songsList = service.fetchAllSongs();
			
			model.addAttribute("songs",songsList);
			
			return "displaySongs";
		}
		else {
			return "makePayment";
		}
	}
	
	 @GetMapping("/addFavorite/{songId}")
	    public String addFavorite(@PathVariable("songId") int songId, HttpSession session) {
	        String userEmail = (String) session.getAttribute("email");
	        Users user = userService.getUser(userEmail);
	        Song song = service.getSongById(songId);
	        user.getFavoriteSongs().add(song);
	        userService.updateUser(user);
	        return "redirect:/viewSongs";
	    }
	    
	    @GetMapping("/favorites")
	    public String viewFavorites(Model model, HttpSession session) {
	        String userEmail = (String) session.getAttribute("email");
	        Users user = userService.getUser(userEmail);
	        List<Song> favoriteSongs = user.getFavoriteSongs();
	        model.addAttribute("favoriteSongs", favoriteSongs);
	        return "displayFavorites";
	    }
	    
	    @GetMapping("/removeFavorite/{songId}")
	    public String removeFavorite(@PathVariable("songId") int songId, HttpSession session) {
	        String userEmail = (String) session.getAttribute("email");
	        Users user = userService.getUser(userEmail);
	        userService.removeFavoriteSong(user.getId(), songId);
	        return "redirect:/favorites";
	    }
}
