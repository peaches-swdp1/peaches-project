package fi.haagahelia.coolreads.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Message {
	@Id
	@GeneratedValue
	private Long id;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createdAt;

	@Column(nullable=false)
	private String content;

	public Message() {
	}

	public Message(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
