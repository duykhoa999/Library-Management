package library.entity;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Sach")
public class Book {
	@Id
	@GeneratedValue
	@Column(name = "MaSach")
	private int id;
	@Column(name = "TuaDe")
	private String tittle;

	@Column(name = "NamXuatBan")
	private int year;
	
	@Column(name = "HinhAnh")
	private String image;
	
	@Column(name = "MoTa")
	private String description;
	
	@Column(name = "SoTrang")
	private int length;
	
	@Column(name = "NgonNgu")
	private String language;
	
	@ManyToOne
	@JoinColumn(name = "MaNXB")
	private Publisher publisher;
	
	@ManyToOne
	@JoinColumn(name = "MaTheLoai")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "MaTacGia")
	private Author author;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private Collection<Book_Copy> bookCoppies;

	public Book() {
		super();
	}

	public Book(int id, String tittle, int year, String image, String description, int length, String language,
			Publisher publisher, Category category, Author author, Collection<Book_Copy> bookCoppies) {
		super();
		this.id = id;
		this.tittle = tittle;
		this.year = year;
		this.image = image;
		this.description = description;
		this.length = length;
		this.language = language;
		this.publisher = publisher;
		this.category = category;
		this.author = author;
		this.bookCoppies = bookCoppies;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Collection<Book_Copy> getBookCoppies() {
		return bookCoppies;
	}

	public void setBookCoppies(Collection<Book_Copy> bookCoppies) {
		this.bookCoppies = bookCoppies;
	}
}
