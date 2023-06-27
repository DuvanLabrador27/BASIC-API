package com.duvan.crudnew.Controller;

import com.duvan.crudnew.Entity.ProductEntity;
import com.duvan.crudnew.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/V1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService){

        this.productService = productService;
    }

    @GetMapping
    public List<ProductEntity> getProducts(){

        return this.productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody ProductEntity product){
        return this.productService.newProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> UpdateProduct(@RequestBody ProductEntity product){

        return this.productService.UpdateProduct(product);
    }

    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Object> delete(@PathVariable("productId") Long id){
        return this.productService.deleteProduct(id);
    }


}
