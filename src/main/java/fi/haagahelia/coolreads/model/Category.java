package fi.haagahelia.coolreads.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private Long categoryId;
	
	@NotEmpty
	@Column(nullable = false)
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<ReadingRecommendation> readingRecommendations;
	
	public Category() {}

	public Category( String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ReadingRecommendation> getReadingRecommendations() {
		return readingRecommendations;
	}

	public void setReadingRecommendations(List<ReadingRecommendation> readingRecommendations) {
		this.readingRecommendations = readingRecommendations;
	}
	
}
