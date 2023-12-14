package fi.haagahelia.coolreads.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fi.haagahelia.coolreads.model.*;
import fi.haagahelia.coolreads.repository.*;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	@Autowired
	private AppUserRepository userRepository;

	@GetMapping("")
	public List<AppUser> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/current")
	public AppUser getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
		if (userDetails == null) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication is required");
		}

		return userRepository.findOneByUsername(userDetails.getUsername())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication is required"));
	}
}