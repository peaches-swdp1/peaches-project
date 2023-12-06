package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Reading Recommendation", description = "Information about the reading recommendations and their categories")
public class ReadingRecommendationRestController {
	@Autowired
	private ReadingRecommendationRepository readingRecommendationRepository;

	@Operation(summary = "List of reading recommendations.", description = "Fetches all reading recommendations.")

	@GetMapping("")
	public List<ReadingRecommendation> getRecommendations() {
		return readingRecommendationRepository.findAllByOrderByCreatedOnDesc();
	}
}
