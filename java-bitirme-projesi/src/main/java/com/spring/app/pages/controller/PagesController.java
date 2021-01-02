package com.spring.app.pages.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.spring.app.model.Book;
import com.spring.app.service.BookService;

@Controller
public class PagesController {
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping("/")
	public String viewHomePage(Model model) {

		return findPaginated(1, model);
		
	}
	
	
	@GetMapping("/bookList")
	public String searchedHomePage(Model model, String keyword) {

		if (keyword != "") {
			model.addAttribute("bookList", bookService.findWithTitle(keyword));
			return "index";
		}
		else {
			model.addAttribute("bookList", bookService.getAllBooks());
			return findPaginated(1, model);
		}
			
	}
	
	
	@GetMapping("/newBookForm")
	public String showNewBookForm(Model model) {

		Book book = new Book();
		model.addAttribute("book", book);
		return "new_book";
	}
	
	@PostMapping("/saveBook")
	public String saveBook(@ModelAttribute("book") Book book) {
		
		book.setId(bookService.getMaxId() + 1);
		bookService.saveBook(book);
		return "redirect:/";

	}
	
	@GetMapping("/updateForm/{id}")
	public String showUpdateForm(@PathVariable(value="id") Long id, Model model) {
		
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "update_book";
		
	}
	
	@PostMapping("/updateBook")
	public String updateBook(@ModelAttribute("book") Book book) {

		bookService.saveBook(book);
		return "redirect:/";

	}
	
	@GetMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable (value="id") Long id) {
		
		bookService.deleteBookById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;
		
		Page<Book> page = bookService.findPage(pageNo, pageSize);
		List<Book> bookList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("bookList", bookList);
		
		return "index";

	}
	

}
