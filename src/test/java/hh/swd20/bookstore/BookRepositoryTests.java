package hh.swd20.bookstore;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository brepository;
    
    @Test
    public void findByTitleShouldReturnBook() {
    	
    	List<Book> books = brepository.findByTitle("The ghost of Haaga-Helia");
    	assertThat(books.get(0).getAuthor()).isEqualTo("Peter Whitesheet");
    }

    @Test
    public void createNewBook() {
    	Category category = new Category("Test Category");
    	Book book = new Book("The man who knew nothing", "Hardy Nubb", 1985, "789-159-357", 55.00, category);
    	brepository.save(book);
    	assertThat(book.getId()).isNotNull();
    }
    
    @Test
    public void deleteBook() {
    	List<Book> books = brepository.findByTitle("Learn to code");
    	brepository.deleteById(books.get(0).getId());
    	assertThat(books.isEmpty());
    }
}
