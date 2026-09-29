package com.fatec.itu.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.itu.demo.entites.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}