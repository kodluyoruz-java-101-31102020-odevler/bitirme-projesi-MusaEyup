package com.spring.app.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.model.Book;
import com.spring.app.service.BookService;

@RestController
@RequestMapping("/rest")
public class BookController {

	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public List<Book> get(){
		return bookService.getAllBooks();
		
	}
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public Book save(@RequestBody Book newBook) {
		newBook.setId(bookService.getMaxId() + 1);
		bookService.saveBook(newBook);
		return newBook;
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public Long delete(@PathVariable("id") Long id) {
		
		 bookService.deleteBookById(id);
		 return id;
	}
	
	
}
