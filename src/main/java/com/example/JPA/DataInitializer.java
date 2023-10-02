package com.example.JPA;

import com.example.JPA.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // anotacao do springFramework para um componete/classe de serive, no caso Injecao de dependencia  = id
public class DataInitializer {

// classe responsavek por iniciar e intanciar values na base de dados
    private final ProductRepository productRepository; // base de dados inserida em memoria  bd(h2), as informacoes serao inseridas e instanciadas em tempo de execucao

    @Autowired
    public DataInitializer(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @PostConstruct // deve ser executado apos a contrucao do bean
    public void InitializeData(){
        //instanciando dados para base de dados aqui,injetando dependencia (id)
        Product product1 = new Product("Lasanha", 200);
        Product product2 = new Product("Feijão", 200);
        Product product3 = new Product("Batata", 200);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }
}
