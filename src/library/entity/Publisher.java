package library.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="NhaXuatBan")
public class Publisher {
	@Id
	@Column(name="MaNXB")
	private String id;
	@Column(name ="DiaChi")
	private String address;
	@Column(name="TenNguoiLienLac")
	private String contactName;
	@Column(name="DienThoai")
	private String phone;
	@Column(name="TenNXB")
	private String pubName;
	
	@OneToMany(mappedBy = "publisher", fetch = FetchType.EAGER)
	private Collection<Book> books;

	public Publisher() {
		super();
	}

	public Publisher(String id, String address, String contactName, String phone, String pubName,
			Collection<Book> books) {
		super();
		this.id = id;
		this.address = address;
		this.contactName = contactName;
		this.phone = phone;
		this.pubName = pubName;
		this.books = books;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPubName() {
		return pubName;
	}

	public void setPubName(String pubName) {
		this.pubName = pubName;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}
}
