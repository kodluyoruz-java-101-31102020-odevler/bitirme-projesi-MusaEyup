package com.spring.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.spring.app.model.Book;

public interface BookService {
	
	public List<Book> getAllBooks();
	
	public Long getMaxId();
	public Book getBookById(Long id);
	public void deleteBookById(Long id);
	public void saveBook(Book book);
	public List<Book> findWithTitle(String keyword);
	public Page<Book> findPage(int pageNo, int pageSize);
	

}
