package library.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "TacGia")
public class Author {
	@Id
	@Column(name = "MaTacGia")
	private String id;
	@Column(name = "DienThoai")
	private String phone;
	@Column(name = "HoTen")
	private String name;

	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	private Collection<Book> books;

	public Author() {
		super();
	}
	
	public Author(String id, String phone, String name, Collection<Book> books) {
		super();
		this.id = id;
		this.phone = phone;
		this.name = name;
		this.books = books;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
