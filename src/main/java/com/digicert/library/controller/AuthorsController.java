package com.digicert.library.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.digicert.library.dto.AuthorDTO;
import com.digicert.library.dto.BookResponseDTO;
import com.digicert.library.service.AuthorService;

@RestController
public class AuthorsController {

	@Autowired
	private AuthorService authorService;

	@GetMapping("/api/authors/list")
	public List<AuthorDTO> list() {
		final List<AuthorDTO> authors = authorService.listAll();
		return authors;
	}

	@GetMapping("/api/authors/{id}")
	public AuthorDTO get(@PathVariable("id") Long id) {
		final AuthorDTO author = authorService.getById(id);
		return author;
	}

	@PostMapping(path = "/api/authors")
	public AuthorDTO create(@Valid @RequestBody AuthorDTO authorDto) {
		final AuthorDTO savedAuthor = authorService.create(authorDto);
		return savedAuthor;
	}

	@PutMapping("/api/authors")
	public AuthorDTO update(@Valid @RequestBody AuthorDTO authorDto) {
		final AuthorDTO savedAuthor = authorService.update(authorDto);
		return savedAuthor;
	}

	@DeleteMapping("/api/authors/{id}")
	public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
		authorService.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}

	@GetMapping("/api/authors/{authorId}/books")
	public List<BookResponseDTO> listAuthorsBooks(@PathVariable("authorId") Long authorId) {
		final List<BookResponseDTO> authors = authorService.listAuthorsBooks(authorId);
		return authors;
	}
	
}
