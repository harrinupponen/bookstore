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
import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) { 
		return (args) -> {
			log.info("save a couple of books");
			Category coding = new Category("Coding");
			categoryRepository.save(coding);
			
			//categoryRepository.save(new Category("Horror")); **DIFFERENT STYLE**
			Category horror = new Category("Horror");
			categoryRepository.save(horror);
			
			Category drama = new Category("Drama");
			categoryRepository.save(drama);
			
			bookRepository.save(new Book("Learn to code", "Carl Codemaster", 2015, "123-456-789", 34.00, coding));
			bookRepository.save(new Book("The ghost of Haaga-Helia", "Peter Whitesheet", 2018, "456-789-123", 52.00, horror));
			
			// **DIFFERENT STYLE** 
			//bookRepository.save(new Book("The ghost of Haaga-Helia", "Peter Whitesheet", 2018, "456-789-123", 52.00, 
			//categoryRepository.findByName("Horror").get(0)));	
			
			// create users (user & admin)
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@bookstore.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@bookstore.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
