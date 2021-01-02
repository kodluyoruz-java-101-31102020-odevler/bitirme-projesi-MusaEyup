package com.spring.app.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.app.model.Book;
import com.spring.app.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{

	
	@Autowired
	private BookRepository bookRepository;
	
	
	public List<Book> getAllBooks() {
		
		return bookRepository.getAllBooks();
	}

	
	@Transactional
	public void saveBook(Book book) {

		this.bookRepository.save(book);
		
	}

	public Long getMaxId() {
		
		if (bookRepository.findMaxBookId() != null) {
			
			return bookRepository.findMaxBookId();
		}
		return 0L;
	}

	
	public Book getBookById(Long id) {
		Optional<Book> optional = Optional.ofNullable(bookRepository.findWithId(id));
		Book book = null;
		if(optional.isPresent()) {
			book = optional.get();
		}
		else {
			throw new RuntimeException("Book not found");
		}

		return book;
	}
	

	@Override
	@Transactional
	public void deleteBookById(Long id) {
		
		bookRepository.deleteById(id);
		
	}

	@Override
	public List<Book> findWithTitle(String keyword) {

		return bookRepository.findWithTitle(keyword);
	}


	@Override
	public Page<Book> findPage(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo -1 , pageSize);
		return this.bookRepository.findAll(pageable);
		
	}


	





	

}
