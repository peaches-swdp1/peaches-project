package fi.haagahelia.coolreads.dto;

public class AddMessageDto {
	private String content;
	
	public AddMessageDto() {
	}
	
	public AddMessageDto(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
