package com.digicert.library.dto;

import java.util.Objects;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import com.digicert.library.model.Author;

public class AuthorDTO {
	
	private long id;
	
	@NotBlank(message = "Author name is required")
	private String name;
	
	private String country;
	
	public AuthorDTO() {
		
	}
	
	public AuthorDTO(Author author) {
		Optional.of(author)
			.ifPresent(t -> {
				this.id = author.getId();
				this.name = author.getName();
				this.country = author.getCountry();
			});
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public Author toAuthor() {
		final Author author = new Author();
		author.setId(id);
		author.setName(name);
		author.setCountry(country);
		return author;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthorDTO other = (AuthorDTO) obj;
		return Objects.equals(country, other.country) && id == other.id && Objects.equals(name, other.name);
	}

}
