package admin.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import library.entity.Auth;
import library.entity.Staff;

@Controller
@Transactional
@RequestMapping("/admin/")
public class StaffController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("staff/index")
	public String staff(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff";
		Query query = session.createQuery(hql);
		List<Staff> list = query.list();
		model.addAttribute("staffs", list);
		return "admin/staff/index-staff";
	}
	
	@RequestMapping(value = "staff/insert", method = RequestMethod.GET)
	public String insertStaff(ModelMap model) {
		model.addAttribute("staff", new Staff());
		
		return "admin/staff/insert-staff";
	}
	
	@RequestMapping(value = "staff/insert", method=RequestMethod.POST)
	public String insertStaff(ModelMap model, @ModelAttribute("staff") Staff staff, RedirectAttributes redirect, BindingResult errors) {
		if(staff.getId().trim().length() == 0) {
			errors.rejectValue("id", "staff", "Không được để trống mã nhân viên!");
		}
		if(staff.getRole().trim().length() == 0) {
			errors.rejectValue("role", "staff", "Không được để trống chức vụ!");
		}
		if(staff.getName().trim().length() == 0) {
			errors.rejectValue("name", "staff", "Không được để trống tên nhân viên!");
		}
		if(staff.getSalary() == null) {
			errors.rejectValue("salary", "staff", "Không được để trống lương!");
		}
		if(staff.getEmail().trim().length() == 0) {
			errors.rejectValue("email", "staff", "Không được để trống email!");
		}
		else if (!validationEmail(staff.getEmail())) {
			errors.rejectValue("email", "staff", "Email không đúng định dạng!");
		}
		if(errors.hasErrors()) {
			return "admin/staff/insert-staff";
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(staff);
			t.commit();
			redirect.addFlashAttribute("message", "Thêm mới thành công!");
		}
		catch (Exception ex) {
			t.rollback();
			redirect.addFlashAttribute("error", "Thêm mới thất bại!");
		}
		finally {
			session.close();
		}
		
		return "redirect:/admin/staff/index.htm";
	}
	
	@RequestMapping(value = "staff/update/{id}", method = RequestMethod.GET)
	public String updateStaff(ModelMap model, @PathVariable String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Staff staff = (Staff) query.uniqueResult();
		model.addAttribute("staff", staff);
		
		return "admin/staff/update-staff";
	}
	
	@RequestMapping(value = "staff/update/{id}", method=RequestMethod.POST)
	public String updateStaff(@PathVariable String id, @Validated @ModelAttribute("staff") Staff staff, RedirectAttributes redirect, BindingResult errors) {
		if(staff.getId().trim().length() == 0) {
			errors.rejectValue("id", "staff", "Không được để trống mã nhân viên!");
		}
		if(staff.getRole().trim().length() == 0) {
			errors.rejectValue("role", "staff", "Không được để trống chức vụ!");
		}
		if(staff.getName().trim().length() == 0) {
			errors.rejectValue("name", "staff", "Không được để trống tên nhân viên!");
		}
		if(staff.getSalary() == null) {
			errors.rejectValue("salary", "staff", "Không được để trống lương!");
		}
		if(errors.hasErrors()) {
			return "admin/staff/update-staff";
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(staff);
			t.commit();
			redirect.addFlashAttribute("message", "Chỉnh sửa thành công!");
		}
		catch (Exception ex) {
			t.rollback();
			redirect.addFlashAttribute("error", "Chỉnh sửa thất bại!");
		}
		finally {
			session.close();
		}
		
		return "redirect:/admin/staff/index.htm";
	}
	
	@RequestMapping(value = "staff/delete/{id}", method=RequestMethod.GET)
	public String deleteStaff(ModelMap model, @PathVariable String id, RedirectAttributes redirect) {
		Session session = factory.openSession();
		String hql = "FROM Staff WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Staff staff = (Staff) query.uniqueResult();
		Transaction t = session.beginTransaction();
		try {
			session.delete(staff);
			t.commit();
			redirect.addFlashAttribute("message", "Xóa thành công!");
		}
		catch (Exception ex) {
			t.rollback();
			redirect.addFlashAttribute("error", "Nhân viên đã có tài khoản! Xóa thất bại!");
		}
		finally {
			session.close();
		}
		
		return "redirect:/admin/staff/index.htm";
	}
	
	@RequestMapping("/staff/auth/index")
	public String auth(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Auth a WHERE a.staff.role <> 'Admin'";
		Query query = session.createQuery(hql);
		List<Auth> list = query.list();
		model.addAttribute("auths", list);
		return "admin/staff/index-auth";
	}
	@ModelAttribute("staffs")
	public List<Staff> getStaff() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Staff s WHERE s.id NOT IN (SELECT staff.id FROM Auth)";
		Query query = session.createQuery(hql);
		List<Staff> list = query.list();
		return list;
	}
	@RequestMapping(value = "staff/auth/insert", method = RequestMethod.GET)
	public String insertAuth(ModelMap model) {
		model.addAttribute("auth", new Auth());
		
		return "admin/staff/insert-auth";
	}
	
	@RequestMapping(value = "staff/auth/insert", method = RequestMethod.POST)
	public String insertAuth(ModelMap model, @ModelAttribute("auth") Auth auth,@RequestParam("password-confirm") String password_confirm ,RedirectAttributes redirect, BindingResult errors) {
		if(auth.getUsername().trim().length() == 0) {
			errors.rejectValue("username", "auth", "Tài khoản không được trống!");
		}
		if(!checkUsername(auth.getUsername().trim())) {
			errors.rejectValue("username", "auth", "Tài khoản đã tồn tại! Mời nhập lại!");
		}
		if(auth.getPassword().trim().length() == 0) {
			errors.rejectValue("password", "auth", "Mật khẩu không được trống!");
		}
		if(!auth.getPassword().equals(password_confirm)) {
			errors.rejectValue("password", "auth", "Xác nhận mật khẩu không trùng khớp!");
		}
		if(errors.hasErrors()) {
			return "admin/staff/insert-auth";
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(auth);
			t.commit();
			redirect.addFlashAttribute("message", "Thêm mới thành công!");
		}
		catch (Exception ex) {
			t.rollback();
			redirect.addFlashAttribute("error", "Thêm mới thất bại!");
		}
		finally {
			session.close();
		}
		 return "redirect:/admin/staff/auth/index.htm"; 
	}
	
	public boolean checkUsername(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Auth WHERE username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		if(query.uniqueResult() != null){
			return false;
		}
		return true;
	}
	
	@RequestMapping(value = "staff/auth/delete/{username}", method=RequestMethod.GET)
	public String deleteAuth(ModelMap model, @PathVariable String username, RedirectAttributes redirect) {
		Session session = factory.openSession();
		String hql = "FROM Auth WHERE username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		Auth auth = (Auth) query.uniqueResult();
		Transaction t = session.beginTransaction();
		try {
			session.delete(auth);
			t.commit();
			redirect.addFlashAttribute("message", "Xóa thành công!");
		}
		catch (Exception ex) {
			t.rollback();
			redirect.addFlashAttribute("error", "Xóa thất bại!");
		}
		finally {
			session.close();
		}
		
		return "redirect:/admin/staff/auth/index.htm";
	}
	
	static boolean validationEmail(String email) {
		   String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		   return email.matches(regex);
	}
}
