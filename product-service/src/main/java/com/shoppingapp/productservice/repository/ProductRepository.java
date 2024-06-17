package com.shoppingapp.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shoppingapp.productservice.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

}
