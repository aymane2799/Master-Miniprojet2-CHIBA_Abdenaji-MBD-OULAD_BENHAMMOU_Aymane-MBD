package com.example.demo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PutMapping
    public void addCategory(@RequestBody Category category){
        categoryService.createCategory(category);
    }

}
