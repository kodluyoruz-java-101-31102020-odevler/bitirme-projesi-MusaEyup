package com.spring.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.app.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	@Query(value = "select b from Book b")
	public List<Book> getAllBooks();
	
	@Query(value = "select max(b.id) from Book b")
	public Long findMaxBookId();
	
	@Query(value = "select b from Book b where b.id = :id")
	public Book findWithId(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM books b WHERE b.book_title like %:keyword%", nativeQuery=true)
	public List<Book> findWithTitle(@Param("keyword") String keyword);
	
	

}
