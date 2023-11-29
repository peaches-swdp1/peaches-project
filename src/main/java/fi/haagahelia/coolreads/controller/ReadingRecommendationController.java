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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	// Edit student
	@RequestMapping(value = "recommendations/edit/{id}", method = RequestMethod.GET)
	public String editRecommendation(@PathVariable("id") Long recommendationId, Model model) {
		ReadingRecommendation readingRecommendation = readingRepository.findById(recommendationId).get();
		model.addAttribute("readingRecommendation", readingRecommendation);
		model.addAttribute("categories", categoryRepository.findAll());

		return "editrecommendation";
	}
	
	// Save new recommendation
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ReadingRecommendation readingRecommendation){
        readingRepository.save(readingRecommendation);
        return "redirect:/";
    }   

	
	@PostMapping("/recommendations/delete")
	public String deleteRecommendation(@RequestParam("id") Long readingRecommendationId) {
	    readingRepository.deleteById(readingRecommendationId);
	    return "redirect:/";
	}

}

