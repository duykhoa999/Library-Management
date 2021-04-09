package library.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DocGia")
public class Reader {
	@Id
	@Column(name = "MaDocGia")
	private String id;
	@Column(name="HoTen")
	private String name;
	@Column(name ="DiaChi")
	private String address;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	@Column(name ="NgayDangKy")
	private Date registrationDate;
	
	@OneToMany(mappedBy = "reader", fetch = FetchType.EAGER)
	private Collection<Loan> loans;

	public Reader() {
		super();
	}

	public Reader(String id, String name, String address, Date registrationDate, Collection<Loan> loans) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.registrationDate = registrationDate;
		this.loans = loans;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Collection<Loan> getLoans() {
		return loans;
	}

	public void setLoans(Collection<Loan> loans) {
		this.loans = loans;
	}
}
