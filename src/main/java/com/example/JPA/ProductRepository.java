package com.example.JPA;

import com.example.JPA.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //interface para realizar operacoes de consulta na base de dados implementando as operacoes CRUD
}
