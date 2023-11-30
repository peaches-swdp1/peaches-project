package fi.haagahelia.coolreads.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;

@RestController
@RequestMapping("/api/recommendations")
public class ReadingRecommendationRestController {
	@Autowired
	private ReadingRecommendationRepository readingRecommendationRepository;
	
	
	@GetMapping("")
	public List<ReadingRecommendation> getRecommendations() {
		return readingRecommendationRepository.findAllByOrderByCreatedOnDesc();
	}
}
