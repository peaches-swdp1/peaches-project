package fi.haagahelia.coolreads.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotEmpty;

public class AddReadingRecommendationDto {
	@NotEmpty
	private String title;
	
	@NotEmpty
	@URL
	private String link;
	
	@NotEmpty
	private String description;
	
	private String categoryName;
	
	public AddReadingRecommendationDto() {}

	public AddReadingRecommendationDto(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public AddReadingRecommendationDto(@NotEmpty String title, @NotEmpty String link, @NotEmpty String description,
			String categoryName) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.categoryName = categoryName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
