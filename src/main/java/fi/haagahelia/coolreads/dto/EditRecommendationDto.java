package fi.haagahelia.coolreads.dto;

public class EditRecommendationDto {
private String content;
	
	public EditRecommendationDto() {
	}
	
	public EditRecommendationDto(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
