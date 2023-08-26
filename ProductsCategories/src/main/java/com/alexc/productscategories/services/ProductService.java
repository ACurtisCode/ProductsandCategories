package com.alexc.productscategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexc.productscategories.models.Category;
import com.alexc.productscategories.models.Product;
import com.alexc.productscategories.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository prodRepo;
	public Product createProduct(Product product) {
		return prodRepo.save(product);
	}
	public List<Product> getAll() {
		return prodRepo.findAll();
	}
	public List<Product> getAllContains(Category category) {
		return prodRepo.findAllByCategories(category);
	}
	public List<Product> getAllNotContains(Category category) {
		return prodRepo.findByCategoriesNotContains(category);
	}
	public Product findProduct(Long id) {
		Optional<Product> optProd = prodRepo.findById(id);
		if (optProd.isPresent()) {
			return optProd.get();
		} else {
			return null;
		}
	}
	public void addCategory(Product product, Category category) {
		List<Category> categories = product.getCategories();
		categories.add(category);
		product.setCategories(categories);
		prodRepo.save(product);
	}
}
