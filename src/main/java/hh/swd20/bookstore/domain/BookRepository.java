package hh.swd20.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//tietokantakäsittelyn rajapinta
public interface BookRepository extends CrudRepository<Book, Long> {
	
	List<Book> findByTitle(String title);

}
