package admin.controller;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import library.entity.Author;
import library.entity.Book;
import library.entity.Book_Copy;
import library.entity.Category;
import library.entity.Publisher;


@Controller
@Transactional
@RequestMapping("/admin/")
public class BookController {
	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	Book b;
	
	@RequestMapping("book/index")
	public String staff(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		model.addAttribute("books", list);
		return "admin/book/index";
	}
	
	@ModelAttribute("categories")
	public List<Category> getCategory(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Category";
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}
	
	@ModelAttribute("publishers")
	public List<Publisher> getPublisher(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Publisher";
		Query query = session.createQuery(hql);
		List<Publisher> list = query.list();
		return list;
	}
	
	@ModelAttribute("authors")
	public List<Author> getAuthor(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Author";
		Query query = session.createQuery(hql);
		List<Author> list = query.list();
		return list;
	}
	
	@RequestMapping(value = "book/insert-book", method = RequestMethod.GET)
	public String insertBook(ModelMap model) {
		model.addAttribute("book", new Book());
		
		return "admin/book/insert-book";
	}
	
	@RequestMapping(value = "book/insert-book", method = RequestMethod.POST)
	public String insertBook(ModelMap model,@RequestParam("attachment") MultipartFile attachment, @ModelAttribute("book") Book book, BindingResult errors) {
		if(book.getTittle().trim().length() == 0) {
			errors.rejectValue("tittle", "book", "Không được để trống tiêu đề!");
		}
		if(book.getYear() < 1900 || book.getYear() > 2021 ) {
			errors.rejectValue("year", "book", "Năm không hợp lệ!");
		}
		if(book.getLanguage().trim().length() == 0) {
			errors.rejectValue("language", "book", "Phải điền 1 ngôn ngữ bất kì!");
		}
		if(book.getLength() <= 0) {
			errors.rejectValue("length", "book", "Số trang không hợp lệ!");
		}
		if(errors.hasErrors()) {
			return "admin/book/insert-book";
		}
		if (attachment.isEmpty()) {
			model.addAttribute("errorImage", "Phải chọn 1 hình!");
			return "admin/book/insert-book";
		}
		else {
			try {
				String path = context.getRealPath("/resources/images-book/" + attachment.getOriginalFilename());
				attachment.transferTo(new File(path));
			}
			catch (Exception e) {
				
			}
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			book.setImage(attachment.getOriginalFilename());
			session.save(book);
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
		
		return "admin/book/insert-book";
	}
	
	@RequestMapping(value = "book/index-bookcopy/{id}")
	public String indexBookCopy(ModelMap model ,@PathVariable int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book_Copy b WHERE b.book.id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Book_Copy> list = query.list();
		model.addAttribute("book_coppies", list);
		model.addAttribute("id", id);
		return "admin/book/index-bookcopy";
	}
	
	@RequestMapping(value = "book/insert-copy/{id}")
	public String insertBookCopy(ModelMap model ,@PathVariable int id, RedirectAttributes redirect) {
		Book_Copy bookCop = new Book_Copy();
		Session session = factory.openSession();
		String hql = "FROM Book WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Book b = (Book) query.uniqueResult();
		bookCop.setBook(b);
		
		Transaction t = session.beginTransaction();
		try {
			session.save(bookCop);
		    t.commit();
		}
		catch (Exception ex) {
			t.rollback();
		}
		finally {
			session.close();
		}
		return this.indexBookCopy(model, id);
	}
	
	@RequestMapping(value = "book/update-book/{id}", method = RequestMethod.GET)
	public String updateBook(ModelMap model, @PathVariable int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		Book book = (Book) query.uniqueResult();
		b = book;
		model.addAttribute("book", book);
		return "admin/book/update-book";
	}
	
	@RequestMapping(value = "book/update-book/{id}", method=RequestMethod.POST)
	public String updateBook(ModelMap model, @PathVariable int id, @ModelAttribute("book") Book book, @RequestParam("attachment") MultipartFile attachment, RedirectAttributes redirect, BindingResult errors) {
		if(book.getTittle().trim().length() == 0) {
			errors.rejectValue("tittle", "book", "Không được để trống tiêu đề!");
		}
		if(book.getYear() < 1900 || book.getYear() > 2021 ) {
			errors.rejectValue("year", "book", "Năm không hợp lệ!");
		}
		if(book.getLanguage().trim().length() == 0) {
			errors.rejectValue("language", "book", "Phải điền 1 ngôn ngữ bất kì!");
		}
		if(book.getLength() <= 0) {
			errors.rejectValue("length", "book", "Số trang không hợp lệ!");
		}
		if(errors.hasErrors()) {
			return "admin/book/insert-book";
		}
		if (attachment.isEmpty()) {
			book.setImage(b.getImage());
		}
		else {
			try {
				String path = context.getRealPath("/resources/images-book/" + attachment.getOriginalFilename());
				attachment.transferTo(new File(path));
				book.setImage(attachment.getOriginalFilename());
			}
			catch (Exception e) {
				redirect.addFlashAttribute("error", "Thêm ảnh thất bại!");
			}
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(book);
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
		
		return "redirect:/admin/book/index.htm";
	}
	
	@RequestMapping(value = "book/insert-author", method = RequestMethod.GET)
	public String insertAuthor(ModelMap model) {
		model.addAttribute("author", new Author());
		
		return "admin/book/insert-author";
	}
	
	@RequestMapping(value = "book/insert-author", method = RequestMethod.POST)
	public String insertAuthor(ModelMap model, @ModelAttribute("author") Author author, BindingResult errors) {
		if(author.getId().trim().length() == 0) {
			errors.rejectValue("id", "author", "Không được để trống mã tác giả!");
		}
		if(author.getName().trim().length() == 0) {
			errors.rejectValue("name", "author", "Không được để trống tên tác giả!");
		}
		if(author.getPhone().trim().length() == 0) {
			errors.rejectValue("phone", "author", "Không được để trống số điện thoại!");
		}
		else if(!validationPhoneNumber(author.getPhone())) {
			errors.rejectValue("phone", "author", "Số điện thoại không đúng định dạng!");
		}
		if(errors.hasErrors()) {
			return "admin/book/insert-author";
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(author);
			t.commit();
			model.addAttribute("message", "Thêm tác giả mới thành công!");
		}
		catch (Exception ex) {
			t.rollback();
			model.addAttribute("error", "Thêm tác giả mới thất bại!");
		}
		finally {
			session.close();
		}
		
		return "admin/book/insert-author";
	}
	
	@RequestMapping(value = "book/insert-cate", method = RequestMethod.GET)
	public String insertCategory(ModelMap model) {
		model.addAttribute("category", new Category());
		
		return "admin/book/insert-category";
	}
	
	@RequestMapping(value = "book/insert-cate", method = RequestMethod.POST)
	public String insertCategory(ModelMap model, @ModelAttribute("category") Category category, BindingResult errors) {
		if(category.getId().trim().length() == 0) {
			errors.rejectValue("id", "category", "Không được để trống mã thể loại!");
		}
		
		if(category.getName().trim().length() == 0) {
			errors.rejectValue("name", "category", "Không được để trống tên thể loại!");
		}
		if(errors.hasErrors()) {
			return "admin/book/insert-category";
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(category);
			t.commit();
			model.addAttribute("message", "Thêm thể loại mới thành công!");
		}
		catch (Exception ex) {
			t.rollback();
			model.addAttribute("error", "Thêm thể loại mới thất bại!");
		}
		finally {
			session.close();
		}
		
		return "admin/book/insert-category";
	}
	
	@RequestMapping(value = "book/insert-pub", method = RequestMethod.GET)
	public String insertPublisher(ModelMap model) {
		model.addAttribute("publisher", new Publisher());
		
		return "admin/book/insert-publisher";
	}
	
	@RequestMapping(value = "book/insert-pub", method = RequestMethod.POST)
	public String insertPublisher(ModelMap model ,@ModelAttribute("publisher") Publisher publisher, BindingResult errors) {
		if(publisher.getId().trim().length() == 0) {
			errors.rejectValue("id", "publisher", "Không được để trống mã nhà xuất bản!");
		}
		if(publisher.getPubName().trim().length() == 0) {
			errors.rejectValue("pubName", "publisher", "Không được để trống tên nhà xuất bản!");
		}
		if(publisher.getPhone().trim().length() == 0) {
			errors.rejectValue("phone", "publisher", "Không được để trống số điện thoại!");
		}
		else if(!validationPhoneNumber(publisher.getPhone())) {
			errors.rejectValue("phone", "publisher", "Số điện thoại không đúng định dạng!");
		}
		if(errors.hasErrors()) {
			return "admin/book/insert-publisher";
		}
		
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(publisher);
			t.commit();
			model.addAttribute("message", "Thêm nhà xuất bản mới thành công!");
		}
		catch (Exception ex) {
			t.rollback();
			model.addAttribute("error", "Thêm nhà xuất bản mới thất bại!");
		}
		finally {
			session.close();
		}
		
		return "admin/book/insert-publisher";
	}
	
	public boolean validationPhoneNumber(String phone) {
		String regex="^\\d{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phone);
		if(matcher.find()) {
			return true;
		}
		return false;
	}
}
