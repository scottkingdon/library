package com.digicert.library.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.digicert.library.model.Author;
import com.digicert.library.model.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookResponseDTO {
	
	private long id;
	private String title;
	private List<AuthorDTO> authors;
	

	public BookResponseDTO() {
		this.authors = new ArrayList<>();
	}
	
	public BookResponseDTO(Book book) {
		this();
		this.id = book.getId();
		this.title = book.getTitle();
		for (Author author : book.getAuthors()) {
			this.authors.add(new AuthorDTO(author));
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDTO> authors) {
		this.authors = authors;
	}

	public Book toBook() {
		Book book = new Book();
		book.setId(getId());
		book.setTitle(getTitle());
		
		List<Author> authors = new ArrayList<>();
		for (AuthorDTO authorDto : getAuthors()) {
			Author author = new Author();
			author.setId(authorDto.getId());
			author.setName(authorDto.getName());
			author.setCountry(authorDto.getCountry());
			authors.add(author);
		}	
		book.setAuthors(authors);
		
		return book;
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
		BookResponseDTO other = (BookResponseDTO) obj;
		return Objects.equals(authors, other.authors) && id == other.id && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", title=" + title + ", authors=" + authors + "]";
	}
	
	
}
