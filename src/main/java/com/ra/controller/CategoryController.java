package com.ra.controller;

import com.ra.model.dto.DataError;
import com.ra.model.entity.Category;
import com.ra.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<Page<Category>> index(
            @RequestParam(name = "page",defaultValue = "0") int page,
            @RequestParam(name = "limit",defaultValue = "3") int limit,
            @RequestParam(name = "sortBy") String sortBy,
            @RequestParam(name = "orderBy",defaultValue = "asc") String orderBy
    ){
        Sort sort = orderBy.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page,limit,sort);
        Page<Category> categories = categoryService.findAll(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Category> create(@RequestBody Category category){
        Category categoryNew = categoryService.save(category);
        return new ResponseEntity<>(categoryNew, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Category category = categoryService.findById(id);
        if(category != null){
            return new ResponseEntity<>(category,HttpStatus.OK);
        }
        return new ResponseEntity<>(new DataError("category Nót Phao",404),HttpStatus.NOT_FOUND);
    }
    @PutMapping("edit/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Category category){
        if(categoryService.findById(id) != null){
            category.setId(id);
            Category categoryUpdate = categoryService.save(category);
            return new ResponseEntity<>(categoryUpdate, HttpStatus.OK);
        }
        return new ResponseEntity<>(new DataError("category Nót Phao",404),HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
