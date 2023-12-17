package fi.haagahelia.coolreads.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.coolreads.dto.AddCategoryDto;
import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.repository.AppUserRepository;
import fi.haagahelia.coolreads.repository.CategoryRepository;

@Controller
public class CategoryController {
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private AppUserRepository appUserRepository;

	@GetMapping("/category")
	public String renderCategoryList(Model model) {

		model.addAttribute("categories", categoryRepository.findAll());

		return "categorylist";
	}

	@GetMapping("/categories/add")
	public String renderAddCategoryForm() {
		return "addcategory";
	}

	@PostMapping("/categories/add")
	public String addCategory(@ModelAttribute AddCategoryDto category, Model model,
			@AuthenticationPrincipal UserDetails userDetails) {
		AppUser user = appUserRepository.findOneByUsername(userDetails.getUsername()).orElse(null);

		if (user == null)
			return "redirect:/category";

		log.info("Goodd");

		Category existingCategory = categoryRepository.findByName(category.getName());

		if (existingCategory != null) {
			model.addAttribute("errorMessage",
					"Category with this name already exists. Please choose a different name.");
			return "addcategory";
		}

		Category newCategory = new Category(category.getName(), user);
		categoryRepository.save(newCategory);

		return "redirect:/category";
	}

}
