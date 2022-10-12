package com.digicert.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digicert.library.dto.BookResponseDTO;
import com.digicert.library.dto.BookUpsertDTO;
import com.digicert.library.service.BookService;

@RestController
public class BooksController {

	@Autowired
	private BookService bookService;

	@GetMapping("/api/books/list")
	public List<BookResponseDTO> list() {
		final List<BookResponseDTO> books = bookService.listAll();
		return books;
	}

	@GetMapping("/api/books/{id}")
	public BookResponseDTO get(@PathVariable("id") Long id) {
		final BookResponseDTO book = bookService.getById(id);
		return book;
	}

	@PostMapping(path = "/api/books")
	public BookResponseDTO create(@RequestBody BookUpsertDTO bookDto) {
		final BookResponseDTO savedBook = bookService.create(bookDto);
		return savedBook;
	}

	@PutMapping("/api/books")
	public BookResponseDTO update(@RequestBody BookUpsertDTO bookDto) {
		final BookResponseDTO savedBook = bookService.update(bookDto);
		return savedBook;
	}

	@DeleteMapping("/api/books/{id}")
	public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
		bookService.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}

}
