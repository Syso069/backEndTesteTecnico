package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.domain.RequestProduct;
import com.example.demo.service.ProductService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testeTecnico")
public class ProdutctControler {

    @Autowired
    private ProductService serviceProduct;

    @GetMapping
    public ResponseEntity getAllProduct(){
        return ResponseEntity.ok(serviceProduct.pegaProducts());
    }

    @PostMapping
    public ResponseEntity registerProduct(@RequestBody @Valid RequestProduct data){
        Product product = new Product(data);
        serviceProduct.saveNewProduct(product);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody @Valid RequestProduct data){
        var atualizaProduct = serviceProduct.updateProductId(id, data);
        return ResponseEntity.ok(atualizaProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        var delete = serviceProduct.deleteProductId(id);
        return ResponseEntity.ok(delete);
    }
}
