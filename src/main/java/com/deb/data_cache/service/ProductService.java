package com.deb.data_cache.service;

import com.deb.data_cache.model.Product;
import com.deb.data_cache.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @CacheEvict(value = "products", allEntries = true)
    public Product addProduct(Product product){
        return productRepository.saveAndFlush(product);
    }

    @Cacheable(value = "products")
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @Caching(put = {@CachePut(value = "products", key = "#product.id")}, evict = {@CacheEvict(value = "products", allEntries = true)})
    public Product update(Product product){
        // put other logics
        return productRepository.saveAndFlush(product);
    }

    @Caching(evict = {@CacheEvict(value = "products", allEntries = true), @CacheEvict(value = "products", key = "#id")})
    public ResponseEntity<?> deleteById(Long id){
        if(!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productRepository.deleteById(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
