package demo.spring.boot.integretion.test.repository;

import javax.transaction.Transactional;
import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.app.model.Book;
import com.spring.app.repository.BookRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource({"classpath:application.properties"})
public class BookRepositoryIT {

	@Autowired
	private BookRepository bookRepository;
	
	
	@Test
	@Order(1)
	public void selectBookByBookNo() {
		
		Long maxId = bookRepository.findMaxBookId();
		Book books = bookRepository.findWithId(maxId);
		
		Assert.assertNotNull(books);
		Assert.assertTrue(books.getId() > 0);
	}
	
	@Test
	@Transactional
	@Order(2)
	@Rollback(true)
	public void saveBook() {
		
		Long maxId = bookRepository.findMaxBookId();
		Long id = maxId + 1;
		
		Book book = new Book();
		book.setId(id);
		book.setTitle("test");
		book.setAuthor("test2");
		book.setDescription("aaaaa");
		book.setNote("not");
		bookRepository.save(book);
		book = bookRepository.findWithId(id);
		Assert.assertNotNull(book);
		Assert.assertTrue(book.getId() > 0);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Order(3)
	public void deleteBooks() {
		
		Long maxId = bookRepository.findMaxBookId();
		Book book = bookRepository.findWithId(maxId);
		
		bookRepository.delete(book);
		
		book = bookRepository.findWithId(maxId);
		
		Assert.assertNull(book);
	}
}
