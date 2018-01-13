package br.com.casadocodigo.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;
import br.com.casadocodigo.loja.validations.ProductValidator;

@RequestMapping("/products")
@Controller
public class ProductsController {
	
	@Autowired
	private ProductDAO productDAO = null;
	
	@RequestMapping(value="form", method= RequestMethod.GET)
	public ModelAndView form(){
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values());
		return modelAndView;
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ModelAndView list(){
		List<Product> products = productDAO.list();
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", products);
		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	public ModelAndView save(@Valid Product product, BindingResult bindingResult ,RedirectAttributes attrs){
		if (bindingResult.hasErrors()) {
			return form();
		}
		productDAO.save(product);
		attrs.addFlashAttribute("msg", "Produto salvo com sucesso!");
		ModelAndView modelAndView = new ModelAndView("redirect:products");
		return modelAndView;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new ProductValidator());
	}
	
}
