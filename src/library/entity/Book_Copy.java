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
@Table(name = "SachCopy")
public class Book_Copy {
	@Id
	@GeneratedValue
	@Column(name = "MaCopy")
	private int id;
	
	@Column(name = "TrangThai")
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "MaSach")
	private Book book;

	@OneToMany(mappedBy = "bookCopy", fetch = FetchType.EAGER)
	private Collection<Loan> loans;

	public Book_Copy() {
		super();
	}

	public Book_Copy(int id, int status, Book book, Collection<Loan> loans) {
		super();
		this.id = id;
		this.status = status;
		this.book = book;
		this.loans = loans;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Collection<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Collection<Loan> loans) {
		this.loans = loans;
	}
}
