package com.digicert.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digicert.library.dao.AuthorDao;
import com.digicert.library.dao.BookDao;
import com.digicert.library.dto.AuthorDTO;
import com.digicert.library.dto.BookResponseDTO;
import com.digicert.library.exception.AuthorAlreadyExistsException;
import com.digicert.library.exception.AuthorInUseException;
import com.digicert.library.exception.AuthorNotFoundException;
import com.digicert.library.model.Author;
import com.digicert.library.model.Book;

@Service
public class AuthorService {
	
	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private BookDao bookDao;
	
	public List<AuthorDTO> listAll() {
		final Iterable<Author> all = authorDao.findAll();
		final List<AuthorDTO> authors = new ArrayList<>();
		for (Author author : all) {
			authors.add(new AuthorDTO(author));
		}
		return authors;
	}
	
	public AuthorDTO getById(long id) {
		final Optional<Author> author = authorDao.findById(id);
		if (author.isPresent()) {
			return new AuthorDTO(author.get());
		}
		throw new AuthorNotFoundException();
	}
	
	public AuthorDTO create(AuthorDTO authorDto) {
		
		final Optional<Author> existingBook = authorDao.findByName(authorDto.getName());
		if (existingBook.isPresent()) {
			throw new AuthorAlreadyExistsException("Author already exists");
		}
		
		final Author author = authorDto.toAuthor();
		
		final Author savedAuthor = authorDao.save(author);
		return new AuthorDTO(savedAuthor);
	}
	
	public AuthorDTO update(AuthorDTO authorDto) {
		
		final Optional<Author> optional = authorDao.findById(authorDto.getId());
		if (optional.isPresent()) {
			
			final Author author = optional.get();
			author.setName(authorDto.getName());
			author.setCountry(authorDto.getCountry());
			
			final Author savedAuthor = authorDao.save(author);
			return new AuthorDTO(savedAuthor);
		}
		
		throw new AuthorNotFoundException();
		
	}
	
	public void deleteById(long id) {
		
		final List<Book> associatedBooks = bookDao.findDistinctByAuthorsId(id);
		if (associatedBooks.isEmpty()) {
			try {
				authorDao.deleteById(id);
			}
			catch (Exception e) {
				throw new AuthorNotFoundException();
			}
		}
		else {
			throw new AuthorInUseException("Author is associated with books");
		}
	}
	
	public List<BookResponseDTO> listAuthorsBooks(long id) {
		final List<Book> associatedBooks = bookDao.findDistinctByAuthorsId(id);
		final List<BookResponseDTO> books = new ArrayList<>();
		for (Book book :associatedBooks) {
			books.add(new BookResponseDTO(book));
		}
		return books;
	}
	
}
