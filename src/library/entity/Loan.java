package library.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="MuonSach")
public class Loan {
	@Id
	@GeneratedValue
	@Column(name = "MaMuon")
	private int id;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name="NgayMuon")
	private Date loanDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name="NgayTra")
	private Date returnDate;
	@Column(name = "TrangThai")
	private int status;
	
	@ManyToOne
	@JoinColumn(name = "MaCopy")
	private Book_Copy bookCopy;
	
	@ManyToOne
	@JoinColumn(name = "MaDocGia")
	private Reader reader;

	public Loan() {
		super();
	}

	public Loan(int id, Date loanDate, Date returnDate, int status, Book_Copy bookCopy, Reader reader) {
		super();
		this.id = id;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
		this.status = status;
		this.bookCopy = bookCopy;
		this.reader = reader;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Book_Copy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(Book_Copy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}
}
