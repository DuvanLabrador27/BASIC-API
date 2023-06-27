package com.duvan.crudnew.Services;

import com.duvan.crudnew.Entity.ProductEntity;
import com.duvan.crudnew.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    HashMap<String,Object> map;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getProducts(){

        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(ProductEntity product) {
        return getObjectResponseEntity(product);
    }

    public ResponseEntity<Object> UpdateProduct(ProductEntity product){
        return getObjectResponseEntity(product);
    }

    private ResponseEntity<Object> getObjectResponseEntity(ProductEntity product) {
        Optional<ProductEntity> ans = productRepository.findProductEntityByProductName(product.getProductName());
        map = new HashMap<>();

        if (ans.isPresent() && product.getId() == null) {
            map.put("error",true);
            map.put("Message","The product already exist!!");

            return new ResponseEntity<>(map, HttpStatus.CONFLICT);
        } else {
            map.put("Message", "The product already created");
              if(product.getId()!=null) {
                  map.put("Message", "The product already update");
              }
              this.productRepository.save(product);
              map.put("data", product);
              return new ResponseEntity<>(map, HttpStatus.CREATED);

        }
    }

    public ResponseEntity<Object> deleteProduct(Long id){
        map = new HashMap<>();
       boolean exist = this.productRepository.existsById(id);
       if(!exist){
           map.put("error",true);
           map.put("Message","The product don't exist whit that ID!!");
           return new ResponseEntity<>(map, HttpStatus.CONFLICT);
       }else {
           productRepository.deleteById(id);
           map.put("Message","The product Already Delete!!");
           return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
       }
    }



}
