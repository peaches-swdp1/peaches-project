package fi.haagahelia.coolreads.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "appuser")
public class AppUser {
	@Id
	@GeneratedValue
	private Long userId;

	@Column(unique = true, nullable = false)
	private String username;

	@JsonIgnore
	@Column(nullable = false)
	private String passwordHash;

	@Column(nullable = false)
	private String role;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
	private List<ReadingRecommendation> readingRecommendations;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
	private List<Category> categories;
	
	public AppUser() {}

	public AppUser(String username, String passwordHash, String role) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ReadingRecommendation> getReadingRecommendations() {
		return readingRecommendations;
	}

	public void setReadingRecommendations(List<ReadingRecommendation> readingRecommendations) {
		this.readingRecommendations = readingRecommendations;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}