package fi.haagahelia.coolreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.coolreads.dto.AddReadingRecommendationDto;

import org.springframework.web.bind.annotation.PathVariable;

import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;


@Controller
public class ReadingRecommendationController {
	@Autowired
	private ReadingRecommendationRepository readingRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/")
	public String renderReccomendationlist(Model model) {
		model.addAttribute("recommendations", readingRepository.findAll());

		return "recommendationlist";
	}

	@GetMapping("/recommendations/add")
	public String renderAddRecommendationForm(Model model) {
		model.addAttribute("readingRecommendationDto", new AddReadingRecommendationDto());	
		model.addAttribute("categories", categoryRepository.findAll());
		return "addreadingrecommendation";
	}

	@PostMapping("/recommendations/add")
	public String addMessage(@ModelAttribute("readingRecommendationDto") AddReadingRecommendationDto readingRecommendation) {
		Category category = categoryRepository.findByName(readingRecommendation.getCategoryName());
		ReadingRecommendation newReadingRecommendation = new ReadingRecommendation(readingRecommendation.getTitle(),
				readingRecommendation.getLink(), readingRecommendation.getDescription(), category);
		readingRepository.save(newReadingRecommendation);

		return "redirect:/"; 
	}

	@GetMapping("/recommendations/edit/{id}")
	public String renderEditRecommendationForm(@PathVariable Long id, Model model) {
	    ReadingRecommendation recommendation = readingRepository.findById(id).get();

	    AddReadingRecommendationDto editDto = new AddReadingRecommendationDto(
	            recommendation.getTitle(),
	            recommendation.getLink(),
	            recommendation.getDescription(),
	            recommendation.getCategory().getName()
	    );

	    model.addAttribute("readingRecommendationDto", editDto);
	    model.addAttribute("categories", categoryRepository.findAll());
	    model.addAttribute("recommendationId", id);

	    return "editrecommendation";
	}

	@PostMapping("/recommendations/edit/{id}")
	public String editRecommendation(@PathVariable Long id, @ModelAttribute("readingRecommendationDto") AddReadingRecommendationDto readingRecommendation) {
	    Category category = categoryRepository.findByName(readingRecommendation.getCategoryName());
	    ReadingRecommendation updatedRecommendation = new ReadingRecommendation(
	            readingRecommendation.getTitle(),
	            readingRecommendation.getLink(),
	            readingRecommendation.getDescription(),
	            category
	    );
	    updatedRecommendation.setId(id);
	    readingRepository.save(updatedRecommendation);

	    return "redirect:/";
	}
	
	@PostMapping("/recommendations/{id}/delete")
	public String deleteRecommendationBuId(@PathVariable("id") Long readingRecommendationId) {
	    readingRepository.deleteById(readingRecommendationId);
	    return "redirect:/";
	}
	
	@PostMapping("/recommendations/delete")
	public String deleteRecommendation(@RequestParam("id") Long readingRecommendationId) {
	    readingRepository.deleteById(readingRecommendationId);
	    return "redirect:/";
	}

}

