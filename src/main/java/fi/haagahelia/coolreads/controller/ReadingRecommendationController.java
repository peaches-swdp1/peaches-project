package fi.haagahelia.coolreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.haagahelia.coolreads.dto.AddReadingRecommendationDto;

import org.springframework.web.bind.annotation.PathVariable;

import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.AppUserRepository;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;

@Controller
public class ReadingRecommendationController {
	@Autowired
	private ReadingRecommendationRepository readingRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AppUserRepository userRepository;

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
	public String addRecommendation(
			@ModelAttribute("readingRecommendationDto") AddReadingRecommendationDto readingRecommendation,
			@AuthenticationPrincipal UserDetails userDetails) {

		Category category = categoryRepository.findByName(readingRecommendation.getCategoryName());

		AppUser user = userRepository.findOneByUsername(userDetails.getUsername()).orElse(null);

		if (user == null)
			return "redirect:/";

		ReadingRecommendation newReadingRecommendation = new ReadingRecommendation(readingRecommendation.getTitle(),
				readingRecommendation.getLink(), readingRecommendation.getDescription(), category, user);
		readingRepository.save(newReadingRecommendation);

		return "redirect:/";
	}

	@GetMapping("/recommendations/edit/{id}")
	public String renderEditRecommendationForm(@PathVariable Long id, Model model) {
		ReadingRecommendation recommendation = readingRepository.findById(id).get();

		AddReadingRecommendationDto editDto = new AddReadingRecommendationDto(recommendation.getTitle(),
				recommendation.getLink(), recommendation.getDescription(), recommendation.getCategory().getName());

		model.addAttribute("readingRecommendationDto", editDto);
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("recommendationId", id);

		return "editrecommendation";
	}

	@PostMapping("/recommendations/edit/{id}")
	public String editRecommendation(@PathVariable Long id,
			@ModelAttribute("readingRecommendationDto") AddReadingRecommendationDto readingRecommendation,
			@AuthenticationPrincipal UserDetails userDetails) {
		Category category = categoryRepository.findByName(readingRecommendation.getCategoryName());

		ReadingRecommendation oldRecommendation = readingRepository.findById(id).orElse(null);

		AppUser user = userRepository.findOneByUsername(userDetails.getUsername()).orElse(null);

		if (user == null || oldRecommendation == null)
			return "redirect:/";

		if (user.getUserId() != oldRecommendation.getAppUser().getUserId())
			return "redirect:/";

		oldRecommendation.setCategory(category);
		oldRecommendation.setDescription(readingRecommendation.getDescription());
		oldRecommendation.setTitle(readingRecommendation.getTitle());
		oldRecommendation.setLink(readingRecommendation.getLink());

		readingRepository.save(oldRecommendation);

		return "redirect:/";
	}

	@PostMapping("/recommendations/{id}/delete")
	public String deleteRecommendationBuId(@PathVariable("id") Long readingRecommendationId) {
		readingRepository.deleteById(readingRecommendationId);
		return "redirect:/";
	}

	@PostMapping("/recommendations/delete")
	public String deleteRecommendation(@RequestParam("id") Long readingRecommendationId,
			@AuthenticationPrincipal UserDetails userDetails) {
		ReadingRecommendation recommendationToDelete = readingRepository.findById(readingRecommendationId).orElse(null);

		AppUser user = userRepository.findOneByUsername(userDetails.getUsername()).orElse(null);

		if (user == null || recommendationToDelete == null) 
			return "redirect:/";

		if (user.getUserId() != recommendationToDelete.getAppUser().getUserId())
			return "redirect:/";

		readingRepository.deleteById(readingRecommendationId);
		return "redirect:/";
	}

}
