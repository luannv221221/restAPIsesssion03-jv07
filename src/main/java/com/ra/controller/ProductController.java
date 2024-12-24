package com.ra.controller;

import com.ra.model.dto.product.ProductRequestDTO;
import com.ra.model.dto.product.ProductResponseDTO;
import com.ra.model.entity.Product;
import com.ra.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll(){
        List<ProductResponseDTO> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@ModelAttribute ProductRequestDTO productRequestDTO){
        ProductResponseDTO productResponseDTO = productService.save(productRequestDTO);
        return new ResponseEntity<>(productResponseDTO,HttpStatus.CREATED);
    }
}
