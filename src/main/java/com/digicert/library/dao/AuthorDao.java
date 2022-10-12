package com.digicert.library.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digicert.library.model.Author;

@Repository
public interface AuthorDao extends CrudRepository<Author, Long> {
	
	Optional<Author> findByName(String name);

}
