package com.digicert.library.dto;

import java.util.Arrays;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.digicert.library.model.Book;

public class BookUpsertDTO {
	
	private long id;
	
	@NotBlank(message = "Book title is required")
	private String title;
	
	private Long[] authorIds;

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

	public Long[] getAuthorIds() {
		return authorIds;
	}

	public void setAuthorIds(Long[] authorIds) {
		this.authorIds = authorIds;
	}

	public Book toBook() {
		Book book = new Book();
		book.setId(getId());
		book.setTitle(getTitle());
		
		return book;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(id);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookUpsertDTO other = (BookUpsertDTO) obj;
		return Arrays.equals(authorIds, other.authorIds) && id == other.id && Objects.equals(title, other.title);
	}
	
	
}
