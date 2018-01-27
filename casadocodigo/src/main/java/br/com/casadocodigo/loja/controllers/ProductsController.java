package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.infra.FileSaver;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@RequestMapping("/products")
@Controller
public class ProductsController {
	
	@Autowired
	private ProductDAO productDAO = null;
	
	@Autowired
	private FileSaver fileSaver = null;
	
	@RequestMapping(value="form", method= RequestMethod.GET)
	public ModelAndView form(Product product){
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@RequestMapping(method= RequestMethod.GET)
	@Cacheable(value="lastProducts")
	public ModelAndView list(){
		List<Product> products = productDAO.list();
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", products);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	@CacheEvict(value="lastProducts", allEntries=true)
	public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult bindingResult ,RedirectAttributes attrs){
		if (bindingResult.hasErrors()) {
			return form(product);
		}
		
		String webPath = fileSaver.write("uploaded-summaries", summary);
		product.setSummaryPath(webPath);
		
		productDAO.save(product);
		attrs.addFlashAttribute("msg", "Produto salvo com sucesso!");
		ModelAndView modelAndView = new ModelAndView("redirect:products");
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public ModelAndView show(@PathVariable("id") Long id){
		ModelAndView view = new ModelAndView("products/show");
		view.addObject("product", productDAO.find(id));
		return view;
	}
	
}
