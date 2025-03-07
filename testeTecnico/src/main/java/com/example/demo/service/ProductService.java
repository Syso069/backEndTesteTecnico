package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.domain.RequestProduct;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> pegaProducts(){
        List<Product> products = repository.findAll(Sort.by("preco").ascending());
        if (products.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado");
        }else {
            return products;
        }
    }

    public Product saveNewProduct(Product newProduct){;
        System.out.println("Método saveNewProduct chamado!");
        System.out.println("Valor de 'disponivel' enviado para salvar: " + newProduct.isDisponivel());
        Product productSave = repository.save(newProduct);

        if (productSave == null){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao salvar produto.");
        }else {
            return productSave;
        }
    }

    public Product updateProductId(Long id, RequestProduct data){
        Optional<Product> products = repository.findById(String.valueOf(id));
        Product product = products.get();
        product.setNome(data.nome());
        product.setDescricao(data.descricao());
        product.setPreco(data.preco());
        product.setDisponivel(data.disponivel());
        return repository.save(product);
    }

    public Product deleteProductId(Long id){
        Optional<Product> products = repository.findById(String.valueOf(id));
        Product product = products.get();
        repository.deleteById(String.valueOf(id));
        return product;
    }
}
