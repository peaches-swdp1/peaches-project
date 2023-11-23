package fi.haagahelia.coolreads.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ReadingRecommendation {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String link;
	
	@Column(nullable=false)
	private String description;
	
	/*
	@CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    */

	public ReadingRecommendation() {}

	public ReadingRecommendation(String title, String link, String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
