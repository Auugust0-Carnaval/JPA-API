package com.example.JPA.model;

//import javax.persistence.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id //anotacao identificando a prop id e desncrevendo a propriedade como ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // anotacao preenchida automaticamente na base de dados (AUTO_INCREMENT)
    private Long id;
    private  String name;
    private double price;

    //construtores
    public Product(String name, double price){
        //constutor padrao sem argumentos
        this.name = name;
        this.price = price;
    }

//    public Product(Long id, String name, double price){
//        this.id = id;
//        this.name = name;
//        this.price = price;
//    }

    public Product() {
        //contrutor sem argumentos
    }

    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
