package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository) { 
		return (args) -> {
			log.info("save a couple of books");
			categoryRepository.save(new Category("Coding"));
			categoryRepository.save(new Category("Horror"));
			categoryRepository.save(new Category("Drama"));
			
			bookRepository.save(new Book("Learn to code", "Carl Codemaster", 2015, "123-456-789", 34.00, categoryRepository.findByName("Coding").get(0)));
			bookRepository.save(new Book("The ghost of Haaga-Helia", "Peter Whitesheet", 2018, "456-789-123", 52.00, categoryRepository.findByName("Horror").get(0)));	
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
