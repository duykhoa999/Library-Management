package home.controller;

import java.security.SecureRandom;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import library.bean.EmailAdmin;
import library.entity.Auth;
import library.entity.Book;
import library.entity.Book_Copy;
import library.entity.Reader;

@Transactional
@Controller
public class HomeController {
	@Autowired
	SessionFactory factory;
	@Autowired
	JavaMailSender mailer;
	@Autowired
	EmailAdmin account;
	
	@RequestMapping("home/index")
	public String index() {
		
		return "home/index";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("user", new Auth());
		
		return "home/login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String index(ModelMap model, HttpSession session, @ModelAttribute("user") Auth user, BindingResult errors) {
		if(user.getUsername().trim().length() == 0)
			errors.rejectValue("username", "auth", "Tên đăng nhập không được bỏ trống!");
		if(user.getPassword().trim().length() == 0)
			errors.rejectValue("password", "auth", "Mật khẩu không được bỏ trống!");
		if(errors.hasErrors())
			return "home/login";
		Auth u = findUser(user.getUsername());
		if (u!= null) {
			if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
				session.setAttribute("mUser", u);
	
				return "redirect:/home/index.htm";
			}	
		}
		model.addAttribute("error", "Sai thông tin đăng nhập!");
		return "home/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletRequest request) {
		session = request.getSession();
		session.removeAttribute("mUser");
		
		return "redirect:/home/index.htm";
	}
	
	@RequestMapping(value = "change-password", method = RequestMethod.GET)
	public String changePassword(HttpSession session, HttpServletRequest request, ModelMap model) {
		session = request.getSession();
		Auth u = new Auth();
		u = (Auth) session.getAttribute("mUser");
		model.addAttribute("user", u);
		
		return "home/change-password";
	}
	
	@RequestMapping(value = "change-password", method = RequestMethod.POST)
	public String changePassword(ModelMap model, HttpSession ss, HttpServletRequest request, @ModelAttribute("user") Auth user) {
		ss = request.getSession();
		Auth u = new Auth();
		u = (Auth) ss.getAttribute("mUser");
		
		user.setUsername(u.getUsername());
		user.setStaff(u.getStaff());
		
		String oldPass = request.getParameter("old_password");
		String cfmPass = request.getParameter("password_confirm");
		if(!oldPass.equals(u.getPassword())) {
			model.addAttribute("errOld", "Old Password does not match!");
			return "home/change-password";
		}
		if(!cfmPass.equals(user.getPassword())) {
			model.addAttribute("errCfm", "Confirm Password does not match!");
			return "home/change-password";
		}
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			
			session.update(user);
			t.commit();
			model.addAttribute("message", "Change Password Success!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("error", "Change Password Failed!");
		}
		finally {
			session.close();
		}
		ss.removeAttribute("mUser");
		ss.setAttribute("mUser", user);
		
		return "home/change-password";
	}
	
	@RequestMapping(value = "forgot-password", method = RequestMethod.GET)
	public String forgotPassword() {
		
		return "home/forgot-password";
	}
	
	@RequestMapping(value = "forgot-password", method = RequestMethod.POST)
	public String forgotPassword(ModelMap model, @RequestParam("username") String username, @RequestParam("email")String email) {
		if(username.trim().length() == 0) {
			model.addAttribute("errorUsername", "Please fill out this field!");
			return "home/forgot-password";
		}
		if(email.trim().length() == 0) {
			model.addAttribute("errorEmail", "Please fill out this field!");
			return "home/forgot-password";
		}
		
		Auth u = findUser(username);
		if(u == null) {
			model.addAttribute("errorUsername", "Username does not match!");
			return "home/forgot-password";
		}
		if(!email.equals(u.getStaff().getEmail().trim())) {
			model.addAttribute("errorEmail", "Email does not match!");
			return "home/forgot-password";
		}
		String newPW = createNewPW(8);
		try {
			// Tạo mail
			MimeMessage mail = mailer.createMimeMessage();
			// Sử dụng lớp trợ giúp
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(account.getUsername(), account.getUsername());
			helper.setTo(u.getStaff().getEmail().trim());
			helper.setReplyTo(account.getUsername(), account.getUsername());
			helper.setSubject("Khôi phục mật khẩu");
			helper.setText("Mật khẩu mới là: " + newPW, true);
			setNewPW(u, newPW);
			// Gửi mail
			mailer.send(mail);
			model.addAttribute("message", "Mail is sent successfully!");
		}
		catch(Exception ex) {
			model.addAttribute("msg", "Sent failed!");
			return "home/forgot-password";
		}
		return "home/forgot-password";
	}
	
	@RequestMapping("book")
	public String book(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		model.addAttribute("books", list);
		return "home/book";
	}
	
	@RequestMapping("book/detail/{id}")
	public String bookDetail(ModelMap model, @PathVariable int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Book book = (Book) query.uniqueResult();
		hql = "SELECT COUNT(status) FROM Book_Copy b WHERE b.book.id = :id";
		query = session.createQuery(hql);
		query.setParameter("id", id);
		long totalCopy = (long) query.uniqueResult();
		hql = "FROM Book_Copy b WHERE b.book.id = :id";
		query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Book_Copy> list = query.list();
		model.addAttribute("b", book);
		model.addAttribute("copies", totalCopy);
		model.addAttribute("bCopies", list);
		return "home/single-book";
	}
	
	@RequestMapping("service")
	public String service(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		model.addAttribute("books", list);
		return "home/service";
	}
	
	
	public Auth findUser(String username) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Auth a WHERE a.username = '" + username + "'";
		Query query = session.createQuery(hql);
		Auth a = (Auth)query.uniqueResult();
		
		return a;
	}
	
	
	public String createNewPW(int len) {

		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(len);
	    for(int i = 0; i < len; i++)
	    	sb.append(AB.charAt(rnd.nextInt(AB.length())));
		
	    return sb.toString();
	}
	
	public void setNewPW(Auth u, String newPW) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		try {
			u.setPassword(newPW);
			ss.save(u);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
	}
}
