package admin.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import library.entity.Book;

@Controller
@Transactional
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM Book";
		Query query = session.createQuery(hql);
		long totalBook = (long) query.uniqueResult();
		hql = "SELECT COUNT(*) FROM Staff";
		query = session.createQuery(hql);
		long totalStaff = (long) query.uniqueResult();
		hql = "SELECT COUNT(*) FROM Reader";
		query = session.createQuery(hql);
		long totalReader = (long) query.uniqueResult();
		hql = "SELECT COUNT(status) FROM Book_Copy WHERE status = 1";
		query = session.createQuery(hql);
		long totalCopy = (long) query.uniqueResult();
		model.addAttribute("book", totalBook);
		model.addAttribute("staff", totalStaff);
		model.addAttribute("reader", totalReader);
		model.addAttribute("copy", totalCopy);
		return "admin/index";
	}
}
