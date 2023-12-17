package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.AppUserRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Reading Recommendation", description = "Information about the reading recommendations and their categories")
public class ReadingRecommendationRestController {
	@Autowired
	private ReadingRecommendationRepository readingRecommendationRepository;

	@Autowired
	private AppUserRepository userRepository;

	@Operation(summary = "List of reading recommendations.", description = "Fetches all reading recommendations.")

	@GetMapping("")
	public List<ReadingRecommendation> getRecommendations() {
		return readingRecommendationRepository.findAllByOrderByCreatedOnDesc();
	}

	@DeleteMapping("/{id}/delete")
	public ResponseEntity<?> deleteRecommendationById(@PathVariable("id") Long readingRecommendationId,
			@AuthenticationPrincipal UserDetails userDetails) {
		ReadingRecommendation recommendationToDelete = readingRecommendationRepository.findById(readingRecommendationId)
				.orElse(null);

		if (recommendationToDelete == null)
			return new ResponseEntity<>("There is no reading recommendation with provided ID", HttpStatus.BAD_REQUEST);

		AppUser user = userRepository.findOneByUsername(userDetails.getUsername()).orElse(null);

		if (user == null || user.getUserId() != recommendationToDelete.getAppUser().getUserId())
			return new ResponseEntity<>("You are not authorized", HttpStatus.UNAUTHORIZED);
		
		readingRecommendationRepository.deleteById(readingRecommendationId);
		return new ResponseEntity<>("Recommendation was successfully deleted", HttpStatus.OK);
	}
}
