package fi.haagahelia.coolreads.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fi.haagahelia.coolreads.dto.AddCategoryDto;
import fi.haagahelia.coolreads.model.Category;
import fi.haagahelia.coolreads.repository.CategoryRepository;

@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepository;

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
	public String addMessage(@ModelAttribute AddCategoryDto category, Model model) {
		Category existingCategory = categoryRepository.findByName(category.getName());
		
		if (existingCategory != null) {
	        model.addAttribute("errorMessage", "Category with this name already exists. Please choose a different name.");
	        return "addcategory";
	    }
		
		Category newCategory = new Category(category.getName());
		categoryRepository.save(newCategory);

		return "redirect:/";
	}

}
