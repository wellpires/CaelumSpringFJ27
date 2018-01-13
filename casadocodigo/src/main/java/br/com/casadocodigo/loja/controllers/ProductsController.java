package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

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
	public String save(Product product){
		productDAO.save(product);
		return "products/ok";
	}
	
	
	
}
