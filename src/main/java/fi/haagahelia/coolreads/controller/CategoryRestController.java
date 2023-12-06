package fi.haagahelia.coolreads.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.model.ReadingRecommendation;
import fi.haagahelia.coolreads.repository.CategoryRepository;
import fi.haagahelia.coolreads.repository.ReadingRecommendationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category", description = "Information about categories and their reading recommendations")
public class CategoryRestController {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ReadingRecommendationRepository readingRepository;

	@Operation(summary = "List of all categories", description = "Fetches all categories.")

	@GetMapping("")
	public List<Category> getCategories() {
		return categoryRepository.findAll();

	}

	@Operation(summary = "Reading recommendations of a category", description = "Fetches all the reading recommendations of a specific category")

	@GetMapping("/{categoryId}/recommendations")
	public ResponseEntity<List<ReadingRecommendation>> getRecommendationsByCategoryId(@PathVariable Long categoryId) {
		Optional<Category> category = categoryRepository.findById(categoryId);

		if (category.isPresent()) {
			List<ReadingRecommendation> recommendations = readingRepository
					.findByCategoryOrderByCreatedOnDesc(category.get());
			return ResponseEntity.ok(recommendations);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
