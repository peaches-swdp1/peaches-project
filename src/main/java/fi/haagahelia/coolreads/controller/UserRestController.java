package fi.haagahelia.coolreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.coolreads.dto.RegisterUserDto;
import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.repository.AppUserRepository;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private AppUserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterUserDto registerUserDto) {

		if (userRepository.findByUsername(registerUserDto.getUsername()) != null) {
			return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
		}

		AppUser newUser = new AppUser();
		newUser.setUsername(registerUserDto.getUsername());
		newUser.setPasswordHash(new BCryptPasswordEncoder().encode(registerUserDto.getPassword()));
		newUser.setRole(registerUserDto.getRole());

		userRepository.save(newUser);

		return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
	}
}
