package com.digicert.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digicert.library.dao.AuthorDao;
import com.digicert.library.dao.BookDao;
import com.digicert.library.dto.BookResponseDTO;
import com.digicert.library.dto.BookUpsertDTO;
import com.digicert.library.exception.AuthorInUseException;
import com.digicert.library.exception.BookAlreadyExistsException;
import com.digicert.library.exception.BookNotFoundException;
import com.digicert.library.model.Author;
import com.digicert.library.model.Book;

@Service
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private AuthorDao authorDao;
	
	public List<BookResponseDTO> listAll() {
		Iterable<Book> all = bookDao.findAll();
		final List<BookResponseDTO> books = new ArrayList<>();
		for (Book book : all) {
			books.add(new BookResponseDTO(book));
		}
		return books;
	}
	
	public BookResponseDTO getById(long id) {
		final Optional<Book> book = bookDao.findById(id);
		if (book.isPresent()) {
			return new BookResponseDTO(book.get());
		}
		throw new BookNotFoundException();
	}
	
	public BookResponseDTO create(BookUpsertDTO bookDto) {
		
		final Optional<Book> existingBook = bookDao.findByTitle(bookDto.getTitle());
		if (existingBook.isPresent()) {
			throw new BookAlreadyExistsException("Title already exists");
		}
		
		final Book book = bookDto.toBook();
		
		addAuthors(bookDto, book);
		
		final Book savedBook = bookDao.save(book);
		return new BookResponseDTO(savedBook);
	}
	
	public BookResponseDTO update(BookUpsertDTO bookDto) {
		
		final Optional<Book> optional = bookDao.findById(bookDto.getId());
		if (optional.isPresent()) {
			
			final Book book = optional.get();
			book.setTitle(bookDto.getTitle());
			
			if (bookDto.getAuthorIds() != null) {
				book.getAuthors().clear();
				addAuthors(bookDto, book);
			}
			
			final Book savedBook = bookDao.save(book);
			return new BookResponseDTO(savedBook);
		}
		
		throw new BookNotFoundException();
		
	}
	
	public void deleteById(long id) {
		try {
			bookDao.deleteById(id);
		}
		catch (Exception e) {
			throw new BookNotFoundException();
		}
	}

	private void addAuthors(BookUpsertDTO bookDto, Book book) {
		if (bookDto.getAuthorIds() != null) {
			for (Long authorId : bookDto.getAuthorIds()) {
				final Optional<Author> optional = authorDao.findById(authorId);
				if (optional.isPresent()) {
					book.getAuthors().add(optional.get());
				}
			}
		}
	}

}
