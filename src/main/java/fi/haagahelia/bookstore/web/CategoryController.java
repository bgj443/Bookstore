package fi.haagahelia.bookstore.web;

import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	//Kaikki restit
    //kategoria
    
    
     //REST service to get all departments
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategoriesRest() {	
        return (List<Category>) categoryRepository.findAll();
    }    

	//REST service to get department by id
    @RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long dId) {	
    	return categoryRepository.findById(dId);
    } 
    
    //REST service to save new department
    @RequestMapping(value="/categories", method = RequestMethod.POST)
    public @ResponseBody Category saveCategoryRest(@RequestBody Category category) {	
    	return categoryRepository.save(category);
    }
    
 
	//näytä kaikki kategoriat
	@RequestMapping(value ="/categorylist", method = RequestMethod.GET)
	public String categoryList(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		model.addAttribute("Category", new Category());
		return "categorylist";	
	}
	   
	//lisää kategoria
	@RequestMapping(value = "/addc")
    public String addCategory(Model model){
    	model.addAttribute("Category", new Category());
    	model.addAttribute("categories", categoryRepository.findAll());
        return "newcategory";
    }
	
   
    //tallenna kategoria
    @RequestMapping(value = "/saveg", method = RequestMethod.POST)
    public String save(Category category){
        categoryRepository.save(category);
        return "redirect:booklist";
    }

}

