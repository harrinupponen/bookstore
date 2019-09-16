package hh.swd20.bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long catId;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Book> booklist;
	
	public Category() {
		}

	public Category(String name) {
		super();
		this.name = name;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBooks(List<Book> booklist) {
		this.booklist = booklist;
	}

	public Long getCatId() {
		return catId;
	}

	public String getName() {
		return name;
	}

	public List<Book> getBooks() {
		return booklist;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", name=" + name + "]";
	}
	
	
	
	
	
}