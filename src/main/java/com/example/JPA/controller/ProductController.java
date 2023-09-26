package com.example.JPA.controller;

import com.example.JPA.ProductRepository;
import com.example.JPA.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import java.util.ArrayList;
import java.util.List;


@RestController // controller de arquuitetura web RESTful
@RequestMapping("/produtos")
public class ProductController {

//    private final List<Product> products = new ArrayList<>(); // modificador de java "final" é aplicado para determiinar uma constante

//    public ProductController(){
//        products.add(new Product(1L, "Amaciante", 10.0));
//        products.add(new Product(2L, "Sabão me pó", 8.90));
//        products.add(new Product(3L, "Fogão", 900.0));
//        products.add(new Product(4L, "Sofa", 950.0));
//    }


    @Autowired // auxilia a conexao com a interface ProductRepository
    private ProductRepository productRepository; // injetando uma instancia e tendo acesso a base de dados definida de interface

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll(); // retorna todos os produtoes da tabela Product
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id).orElse(null); // buscando produto na base de dados, caso nao tenha valor retorna nulo
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productRepository.save(product); // criando objeto e instanciado na base de dados
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product){
        if(productRepository.existsById(id)){
            product.setId(id);
            return productRepository.save(product);
        }

        return null;
//        return (Product) ResponseEntity.notFound();
    }

    @DeleteMapping("/{id}")
    public  Product deleteProdut(@PathVariable long id){
        Product product = productRepository.findById(id).orElse(null);
        if(product != null){
            productRepository.delete(product);
        }
        return product;
    }
}
