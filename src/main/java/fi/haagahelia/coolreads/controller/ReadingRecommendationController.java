package fi.haagahelia.coolreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.coolreads.dto.AddReadingRecommendationDto;
import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;

@Controller
public class ReadingRecommendationController {
	@Autowired
	private ReadingRecommendationRepository readingRepository;
	
	@GetMapping("/")
	public String renderReccomendationlist(Model model) {
		model.addAttribute("recommendations", readingRepository.findAll());
		
		return "recommendationlist";
	}
	

	@GetMapping("/recommendations/add")
	public String renderAddRecommendationForm() {
		return "addreadingrecommendation";
	}

	@PostMapping("/recommendations/add")
	public String addMessage(@ModelAttribute AddReadingRecommendationDto readingRecommendation) {
		ReadingRecommendation newReadingRecommendation = new ReadingRecommendation(readingRecommendation.getTitle(),
				readingRecommendation.getLink(), readingRecommendation.getDescription());
		readingRepository.save(newReadingRecommendation);

		return "redirect:/"; // link to readinglist
	}
}
