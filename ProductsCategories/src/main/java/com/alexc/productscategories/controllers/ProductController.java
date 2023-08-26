package com.alexc.productscategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alexc.productscategories.models.Category;
import com.alexc.productscategories.models.Product;
import com.alexc.productscategories.services.CategoryService;
import com.alexc.productscategories.services.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productServ;
	@Autowired
	CategoryService catServ;
	
	@GetMapping("/")
	public String homePage(Model model) {
		List<Product> products = productServ.getAll();
		List<Category> categories = catServ.getAll();
		model.addAttribute("categories", categories);
		model.addAttribute("products", products);
		return "Home.jsp";
	}
	
	@GetMapping("/products/new")
	public String newProduct(@ModelAttribute("newProduct") Product product, Model model) {
		
		return "NewProduct.jsp";
	}
	@PostMapping("/products/add")
	public String addProduct(@Valid @ModelAttribute("newProduct") Product product, Model model, BindingResult result) {
		if(result.hasErrors()) {
			return "NewProduct.jsp";
		}
		else {
			productServ.createProduct(product);
			return "redirect:/";
		}
	}
	
	@GetMapping("/categories/new")
	public String newCategory(@ModelAttribute("newCategory") Category category, Model model) {
		
		return "NewCategory.jsp";
	}
	@PostMapping("/categories/add")
	public String addCategory(@Valid @ModelAttribute("newCategory") Category category, Model model, BindingResult result) {
		if(result.hasErrors()) {
			return "NewCategory.jsp";
		}
		else {
			catServ.addCategory(category);
			return "redirect:/";
		}		
	}
	
	@GetMapping("/products/{id}")
	public String productPage(@PathVariable("id") Long id, Model model) {
		Product product = productServ.findProduct(id);
		List<Category> categoriesOwn = catServ.getAllContains(product);
		List<Category> categoriesNotOwn = catServ.getAllNotContains(product);
		model.addAttribute("product", product);
		model.addAttribute("categoriesOwn", categoriesOwn);
		model.addAttribute("categoriesNot", categoriesNotOwn);
		return "Products.jsp";
	}
	@PostMapping("/products/{id}")
	public String joinCategory(@PathVariable("id") Long id, @RequestParam(value="addCategory") Long catId, Model model) {
		Product product = productServ.findProduct(id);
		Category category = catServ.findCategory(catId);
		productServ.addCategory(product, category);
		return "redirect:/products/" + id;
	}
	
	@GetMapping("/categories/{id}")
	public String categoryPage(@PathVariable("id") Long id, Model model) {
		Category category = catServ.findCategory(id);
		List<Product> productsOwn = productServ.getAllContains(category);
		List<Product> productsNotOwn = productServ.getAllNotContains(category);
		model.addAttribute("category", category);
		model.addAttribute("productsOwn", productsOwn);
		model.addAttribute("productsNot", productsNotOwn);
		return "Categories.jsp";
	}
	@PostMapping("/categories/{id}")
	public String joinProduct(@PathVariable("id") Long id, @RequestParam(value="addProduct") Long prodId, Model model) {
		Category category = catServ.findCategory(id);
		Product product = productServ.findProduct(prodId);
		catServ.addProduct(category, product);
		return "redirect:/categories/" + id;
	}
}
























