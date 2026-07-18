package com.deltech.backend.service;

import com.deltech.backend.entity.Product;
import com.deltech.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByCategoryAscCreatedAtDesc();
    }

    public Map<String, List<Product>> getCategorizedProducts() {
        List<Product> all = productRepository.findAllByOrderByCategoryAscCreatedAtDesc();
        return all.stream().collect(Collectors.groupingBy(Product::getCategory));
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product details) {
        Product existing = getProductById(id);
        existing.setNameProduct(details.getNameProduct());
        existing.setDescriptionProduct(details.getDescriptionProduct());
        existing.setPriceProduct(details.getPriceProduct());
        existing.setCurrency(details.getCurrency());
        if (details.getImgProduct() != null) {
            existing.setImgProduct(details.getImgProduct());
        }
        existing.setStockProduct(details.getStockProduct());
        existing.setCategory(details.getCategory());
        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
