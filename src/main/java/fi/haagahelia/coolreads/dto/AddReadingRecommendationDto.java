package fi.haagahelia.coolreads.dto;

import jakarta.validation.constraints.NotEmpty;

public class AddReadingRecommendationDto {
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String link;
	
	@NotEmpty
	private String description;
	
	public AddReadingRecommendationDto() {}

	public AddReadingRecommendationDto(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
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
}
