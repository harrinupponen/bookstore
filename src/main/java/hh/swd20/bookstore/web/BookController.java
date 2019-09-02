package hh.swd20.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
	
	@GetMapping("/index")
	public String hello(Model model) {
		String greeting = "Hello Bookstore!";
		model.addAttribute("greeting", greeting);
		return "home";
	}
}
