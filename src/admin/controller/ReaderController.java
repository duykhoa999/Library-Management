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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import library.entity.Reader;

@Controller
@Transactional
@RequestMapping("/admin/")
public class ReaderController {
	@Autowired
	SessionFactory factory;
	Reader r;
	
	@RequestMapping("reader/index")
	public String index(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Reader";
		Query query = session.createQuery(hql);
		List<Reader> list = query.list();
		model.addAttribute("readers", list);
		return "admin/reader/index";
	}
	
	@RequestMapping(value = "reader/update/{id}", method = RequestMethod.GET)
	public String updateStaff(ModelMap model, @PathVariable String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Reader WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Reader reader = (Reader) query.uniqueResult();
		r = reader;
		model.addAttribute("reader", reader);
		
		return "admin/reader/update";
	}
	
	@RequestMapping(value = "reader/update/{id}", method=RequestMethod.POST)
	public String updateStaff(@PathVariable String id, @Validated @ModelAttribute("reader") Reader reader, RedirectAttributes redirect, BindingResult errors) {
		if(reader.getName().trim().length() == 0) {
			errors.rejectValue("name", "reader", "Không được để trống tên độc giả!");
		}
		if(reader.getAddress().trim().length() == 0) {
			errors.rejectValue("address", "reader", "Không được để trống địa chỉ!");
		}
		if(errors.hasErrors()) {
			return "admin/reader/update";
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			reader.setRegistrationDate(r.getRegistrationDate());
			session.update(reader);
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
		
		return "redirect:/admin/reader/index.htm";
	}
}
