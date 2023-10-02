package com.example.JPA.controller;

import com.example.JPA.ProductRepository;
import com.example.JPA.model.Product;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import java.util.ArrayList;
import java.util.List;


@RestController // controller de arquuitetura web RESTful
@RequestMapping("/produtos")
public class ProductController {

    @Autowired // auxilia a conexao com a interface ProductRepository
    private ProductRepository productRepository; // injetando uma instancia e tendo acesso a base de dados definida de interface

    @GetMapping
    public List<Product> getAllProducts(){
        return productRepository.findAll(); // retorna todos os produtoes da tabela Product
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            Product product = productRepository.findById(id).orElse(null);

            if (product != null) {
                return ResponseEntity.ok(product); // retornando um 200 OK com o produto da base de dados
            } else {
                //retorna um badrequest com uma mensagem personalizada em body() == no corpo  de resposta
                return ResponseEntity.badRequest().body(String.format("Produto com ID:%d, não encontrado!! ", id));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product){

        try {
            productRepository.save(product); // criando objeto e instanciado na base de dados

            String messageSaveProduct = String.format("O %s foi salvo com sucesso", product.getName().toString());
            return ResponseEntity.ok().body(messageSaveProduct);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")

    //coloca uma classe apos o modificador de acesso é o tipo de retornn da fuction
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product){
        try {
            if(productRepository.existsById(id)){
                product.setId(id);

                Product updatedProduct = productRepository.save(product);
                return ResponseEntity.ok(updatedProduct); // retorna o objetok product atualizado com sucesso
            }
            else{
                String errorMesage = String.format("Produto com ID: %d não foi encotrado para atualizar", id);
                return ResponseEntity.badRequest().body(errorMesage);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/{id}") // tipo de retorno da funcao sera String da classe ResponseEntity
    public ResponseEntity<String> deleteProdut(@PathVariable long id){
        Product product = productRepository.findById(id).orElse(null);
        if(product != null){
            productRepository.delete(product);
        }
        return ResponseEntity.ok().body(String.format("$s deletado com sucesso, ID:%d",product.getName(),id));
    }
}
