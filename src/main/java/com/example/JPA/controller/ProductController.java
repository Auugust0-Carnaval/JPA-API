package com.example.JPA.controller;

import com.example.JPA.model.Product;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController // controller de arquuitetura web RESTful
@RequestMapping("/products")
public class ProductController {
    private final List<Product> products = new ArrayList<>(); // modificador de java "final" é aplicado para determiinar uma constante

    public ProductController(){
        products.add(new Product(1L, "Amaciante", 10.0));
        products.add(new Product(2L, "Sabão me pó", 8.90));
        products.add(new Product(3L, "Fogão", 900.0));
        products.add(new Product(4L, "Sofa", 950.0));
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return null;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return null;
    }

    @PutMapping
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        return null;
    }

    @DeleteMapping
    public  Product deleteProdut(@PathVariable long id){
        return null;
    }

}
