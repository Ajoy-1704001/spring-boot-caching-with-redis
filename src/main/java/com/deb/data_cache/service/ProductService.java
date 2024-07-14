package com.deb.data_cache.service;

import com.deb.data_cache.model.Product;
import com.deb.data_cache.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        return productRepository.saveAndFlush(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public Product update(Product product){
        // put other logics
        return productRepository.saveAndFlush(product);
    }

    public ResponseEntity<?> deleteById(Long id){
        if(!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
