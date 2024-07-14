package com.deb.data_cache.controller;

import com.deb.data_cache.model.Product;
import com.deb.data_cache.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProducts(@RequestBody Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(productService.getAll());
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product){
        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return productService.deleteById(id);
    }


}
