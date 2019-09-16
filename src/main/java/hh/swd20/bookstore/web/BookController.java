package hh.swd20.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;

@Controller
public class BookController {
	
	@GetMapping("/index")
	public String hello(Model model) {
		String greeting = "Hello Bookstore!";
		model.addAttribute("greeting", greeting);
		return "home";
	}
	
	@Autowired
	BookRepository bookRepository; 
	
	//Listing all books
	@GetMapping(value = "/booklist")
	public String getAllBooks(Model model) {
			List<Book> booklist =  (List<Book>) bookRepository.findAll(); // get books from the database
			model.addAttribute("booklist", booklist); // booklist from the template to the Model
			return "booklist"; // DispatherServlet gets the template name and calls the booklist.html
								// Will be generated on the server.
	}
	
	// empty form
		@GetMapping(value = "/addbook")
		public String getNewBookForm(Model model) {
			model.addAttribute("book", new Book()); // empty book object
			return "bookform";
		}

		// receive and save data from the form
		@PostMapping(value = "/addbook")
		public String addBook(@ModelAttribute Book book) {
			bookRepository.save(book);
			return "redirect:/booklist";
		}
		
		// find book by id
		@GetMapping(value="/editbook/{id}")
		public String findBookById(@PathVariable("id") Long bookId, Model model) {
			model.addAttribute("book", bookRepository.findById(bookId));
			return "editbook";
		}
		
		// edit and save book by id
		@PostMapping(value ="/editbook")
		public String editBookById(@ModelAttribute Book book) {
			bookRepository.save(book);
			return "redirect:/booklist";
		}
		
		// delete book
		@GetMapping(value = "/deletebook/{id}")
		public String deleteBook(@PathVariable("id") Long bookId) {
			bookRepository.deleteById(bookId);
			return "redirect:../booklist";
		}
}
