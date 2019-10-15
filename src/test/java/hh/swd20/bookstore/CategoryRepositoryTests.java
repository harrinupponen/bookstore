package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository crepository;
    
    @Test
    public void findByNameShouldReturnCategory() {
    	List<Category> categories = crepository.findByName("Horror");
    	assertThat(categories.get(0).getCatId()).isNotNull();
    }

    @Test
    public void createNewCategory() {
    	Category cat = new Category("Test");
    	crepository.save(cat);
    	assertThat(cat.getCatId()).isNotNull();
    }
    
    @Test
    public void deleteCategory() {
    	List<Category> categories = crepository.findByName("Drama");
    	crepository.deleteById(categories.get(0).getCatId());
    	assertThat(categories.isEmpty());
    }
}
