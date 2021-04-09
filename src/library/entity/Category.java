package library.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TheLoai")
public class Category {
	@Id
	@Column(name = "MaTheLoai")
	private String id;
	@Column(name = "TenTheLoai")
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Collection<Book> books;

	public Category() {
		super();
	}

	public Category(String id, String name, Collection<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.books = books;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
	
}
