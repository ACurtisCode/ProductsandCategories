package com.alexc.productscategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexc.productscategories.models.Category;
import com.alexc.productscategories.models.Product;
import com.alexc.productscategories.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository catRepo;
	
	public Category addCategory(Category category) {
		return catRepo.save(category);
	}
	public List<Category> getAll() {
		return catRepo.findAll();
	}
	public List<Category> getAllContains(Product product) {
		return catRepo.findAllByProducts(product);
	}
	public List<Category> getAllNotContains(Product product) {
		return catRepo.findByProductsNotContains(product);
	}
	public Category findCategory(Long id) {
		Optional<Category> optCat = catRepo.findById(id);
		if (optCat.isPresent()) {
			return optCat.get();
		} else {
			return null;
		}
	}
	public void addProduct(Category category, Product product) {
		List<Product> products = category.getProducts();
		products.add(product);
		category.setProducts(products);
		catRepo.save(category);
	}
}
