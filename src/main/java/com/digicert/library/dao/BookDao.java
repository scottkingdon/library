package com.digicert.library.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digicert.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Long> {
	
	Optional<Book> findByTitle(String title);
	
	List<Book> findDistinctByAuthorsId(Long authorId);

}
