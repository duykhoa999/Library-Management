package library.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DangNhap")
public class Auth{
	
	@Id
	@Column(name = "TenDangNhap")
	private String username;
	@Column(name ="MatKhau")
	private String password;
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "MaNhanVien", referencedColumnName = "MaNhanVien")
	private Staff staff;

	public Auth() {
		super();
	}

	public Auth(String username, String password, Staff staff) {
		super();
		this.username = username;
		this.password = password;
		this.staff = staff;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}

