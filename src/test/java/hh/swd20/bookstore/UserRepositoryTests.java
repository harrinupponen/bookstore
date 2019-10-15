package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository urepository;
    
    @Test
    public void findByUsernameShouldReturnUser() {
    	User user = urepository.findByUsername("user");
    	assertThat(user.getEmail()).isEqualTo("user@bookstore.com");
    }

    @Test
    public void createNewUser() {
    	User testUser = new User("testuser", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "testuser@bookstore.com", "USER");
    	urepository.save(testUser);
    	assertThat(testUser.getId()).isNotNull();
    }
    
    @Test
    public void deleteBook() {
    	User user = urepository.findByUsername("user");
    	urepository.deleteById(user.getId());
    	assertThat(user.getId().equals(null));
    }
}
