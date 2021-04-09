package service.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import library.entity.Book;
import library.entity.Book_Copy;
import library.entity.Loan;
import library.entity.Reader;

@Controller
@Transactional
@RequestMapping("/service/")
public class ServiceController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "card/add-card", method = RequestMethod.GET)
	public String addcard(ModelMap model) {
		model.addAttribute("reader", new Reader());
		
		return "home/card";
	}
	
	@RequestMapping(value = "card/add-card", method = RequestMethod.POST)
	public String addcard(ModelMap model, @ModelAttribute("reader") Reader reader, BindingResult errors) {
		if(reader.getId().trim().length() == 0) {
			errors.rejectValue("id", "reader", "Please fill out this field!");
		}
		if(reader.getName().trim().length() == 0) {
			errors.rejectValue("name", "reader", "Please fill out this field!");
		}
		if(reader.getAddress().trim().length() == 0) {
			errors.rejectValue("address", "reader", "Please fill out this field!");
		}
		if(errors.hasErrors()) {
			return "home/card";
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			reader.setRegistrationDate(new Date());
			session.save(reader);
			t.commit();
			model.addAttribute("message", "Thêm mới thành công!");
		}
		catch (Exception ex) {
			t.rollback();
			model.addAttribute("error", "Thêm mới thất bại!");
		}
		finally {
			session.close();
		}
		return "home/card";
	}
	
	@RequestMapping("borrow")
	public String indexBorrow() {
		
		return "home/borrow/check";
	}
	
	@RequestMapping("borrow/check")
	public String indexBorrow(ModelMap model,HttpSession session ,@RequestParam("id") String id, RedirectAttributes redirect) {
		Reader r = findReader(id);
		if (r == null) {
			model.addAttribute("error", "Invalid card! Please enter again!");
			return "home/borrow/check";
		}
		session.setAttribute("uid", id);
		return "redirect:/book.htm";
	}
	@RequestMapping(value = "borrow/submit/{id}/{uid}", method = RequestMethod.GET)
	public String submit(ModelMap model, @PathVariable("id") int id, @PathVariable("uid") String uid ,RedirectAttributes redirect) {
		Loan loan = new Loan();
		Session session = factory.openSession();
		String hql = "FROM Book_Copy WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Book_Copy bCopies = (Book_Copy) query.uniqueResult();
		hql = "FROM Reader WHERE id = :id";
		query = session.createQuery(hql);
		query.setParameter("id", uid);
		Reader reader = (Reader) query.uniqueResult();
		Transaction t = session.beginTransaction();
		try {
			loan.setBookCopy(bCopies);
			loan.setReader(reader);
			loan.setLoanDate(new Date());
			LocalDate today = LocalDate.now();
			int day = today.getDayOfMonth() + 7;
			int month = today.getMonthValue();
			int year = today.getYear();
			String returnDay = String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year);
			loan.setReturnDate(new SimpleDateFormat("dd/MM/yyyy").parse(returnDay));
			bCopies.setStatus(1);
			session.update(bCopies);
			session.save(loan);
			t.commit();
			redirect.addFlashAttribute("message", "Add to list successful!");
		}
		catch (Exception ex) {
			t.rollback();
			redirect.addFlashAttribute("error", "Add to list failed!");
		}
		finally {
			session.close();
		}
		
		return "redirect:/book.htm";
	}
	
	@RequestMapping("return")
	public String checkReturn() {
		
		return "home/return/check";
	}
	
	@RequestMapping("return/index")
	public String checkReturn(ModelMap model, @RequestParam("id") String id, RedirectAttributes redirect) {
		Reader r = findReader(id);
		if (r == null) {
			model.addAttribute("error", "Invalid card! Please enter again!");
			return "home/return/check";
		}
		redirect.addFlashAttribute("uid", id);
		Session session = factory.getCurrentSession();
		String hql = "FROM Loan l WHERE l.reader.id = '" + id + "'";
		Query query = session.createQuery(hql);
		List<Loan> list = query.list();
		model.addAttribute("loans", list);
		return "home/return/index";
	}
	
	@RequestMapping("return/submit/{lid}/{bid}")
	public String submitReturn(ModelMap model, @PathVariable int lid, @PathVariable int bid, RedirectAttributes redirect) {
		Session session = factory.openSession();
		String hql = "FROM Loan WHERE id = '" + lid + "'";
		Query query = session.createQuery(hql);
		Loan loan = (Loan) query.uniqueResult();
		
		hql = "FROM Book_Copy WHERE id = '" + bid + "'";
		query = session.createQuery(hql);
		Book_Copy book = (Book_Copy) query.uniqueResult();
		
		Transaction t = session.beginTransaction();
		try {
			loan.setStatus(1);
			book.setStatus(0);
			session.update(book);
			session.update(loan);
			t.commit();
			redirect.addFlashAttribute("message", "Return successful!");
		}
		catch (Exception ex) {
			t.rollback();
			redirect.addFlashAttribute("error", "Return failed!");
		}
		finally {
			session.close();
		}
		return this.checkReturn(model, loan.getReader().getId(), redirect);
	}
	
	public Reader findReader (String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Reader r WHERE r.id = '" + id + "'";
		Query query = session.createQuery(hql);
		Reader a = (Reader)query.uniqueResult();
		return a;
	}
}
