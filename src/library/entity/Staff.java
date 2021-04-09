package library.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "NhanVien")
public class Staff{
	@Id
	@Column(name = "MaNhanVien")
	private String id;
	@Column(name = "ChucVu")
	private String role;
	@Column(name = "HoTen")
	private String name;
	@Column(name = "Luong")
	private BigDecimal salary;
	@Column(name = "Email")
	private String email;
	@OneToOne(mappedBy = "staff")
	private Auth auth;
	
	public Staff() {
		super();
	}

	public Staff(String id, String role, String name, BigDecimal salary, String email, Auth auth) {
		super();
		this.id = id;
		this.role = role;
		this.name = name;
		this.salary = salary;
		this.email = email;
		this.auth = auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Auth getAuth() {
		return auth;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	
}
