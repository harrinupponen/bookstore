package hh.swd20.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	
	@Autowired
	BookRepository bookRepository; 
	
	@Autowired
	CategoryRepository categoryRepository;
	
	// Show login form
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	@GetMapping("/index")
	public String hello(Model model) {
		String greeting = "Hello Bookstore!";
		model.addAttribute("greeting", greeting);
		return "home";
	}
	
	
	//Listing all books
	@GetMapping(value = "/booklist")
	public String getAllBooks(Model model) {
			List<Book> booklist =  (List<Book>) bookRepository.findAll(); // get books from the database
			model.addAttribute("booklist", booklist); // booklist from the template to the Model
			return "booklist"; // DispatcherServlet gets the template name and calls the booklist.html
								// Will be generated on the server.
	}
	
	// *****REST******
	
	// Fetch all
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value="/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>) bookRepository.findAll();
	}
	
	// Show one book by id
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value="/book/{id}")
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }
	
	// Add a new book
	@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value="/books")
    public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
    	return bookRepository.save(book);
    }
    
    //**************
	
	// empty form
    	@PreAuthorize("hasAuthority('ADMIN')")
		@GetMapping(value = "/addbook")
		public String getNewBookForm(Model model) {
			model.addAttribute("book", new Book()); // empty book object
			model.addAttribute("categories", categoryRepository.findAll());
			return "addbook";
		}

		// receive and save data from the form
		@PostMapping(value = "/addbook")
		public String addBook(@ModelAttribute Book book) {
			bookRepository.save(book);
			return "redirect:/booklist";
		}
		
		// find book by id
		@PreAuthorize("hasAuthority('ADMIN')")
		@GetMapping(value="/editbook/{id}")
		public String findBookById(@PathVariable("id") Long bookId, Model model) {
			model.addAttribute("book", bookRepository.findById(bookId));
			return "editbook";
		}
		
		// save book by id
		@PostMapping(value ="/editbook")
		public String editBookById(@ModelAttribute Book book) {
			bookRepository.save(book);
			return "redirect:/booklist";
		}
		
		// delete book
		@PreAuthorize("hasAuthority('ADMIN')")
		@GetMapping(value = "/deletebook/{id}")
		public String deleteBook(@PathVariable("id") Long bookId) {
			bookRepository.deleteById(bookId);
			return "redirect:../booklist";
		}
}
