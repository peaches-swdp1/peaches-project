package fi.haagahelia.coolreads.dto;

import jakarta.validation.constraints.NotEmpty;

public class AddCategoryDto {
	@NotEmpty
	private String name;

	public AddCategoryDto() {	}

	public AddCategoryDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
