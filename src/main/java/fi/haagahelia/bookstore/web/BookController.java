package fi.haagahelia.bookstore.web;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	
	//login
	@RequestMapping(value ="/login")
	public String login() {
		return "login";
	}
	
	
	
	//show all books
	@RequestMapping(value ="/booklist", method = RequestMethod.GET)
	public String bookList(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";	
	}
	//Kaikki restit
	//book
	
	//REST get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) bookRepository.findAll();
    }    

    //REST get book by id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return bookRepository.findById(bookId);
    }      
    
    //REST save new book
    @RequestMapping(value="/books", method = RequestMethod.POST)
    public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
    	return bookRepository.save(book);
    }
	 
	//lisää kirja
	@RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }   

	
	//tallenna kirja
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book Book){
        bookRepository.save(Book);
        return "redirect:booklist";
    }    

    
    // poista kirja
    @PreAuthorize("hasAuthority('ADMIN')") //tarkistaa käyttäjän ja estää poiston myös syötekentässä
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }     
    
    //muokkaa
    @RequestMapping(value = "/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("book", bookRepository.findById(id));
    	model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    } 
    //päivitä
    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String saveEdit(@PathVariable("id")long id, Book book, Model model) {
		book.setId(id);
		bookRepository.save(book);
		return "redirect:../booklist";
	}
}
