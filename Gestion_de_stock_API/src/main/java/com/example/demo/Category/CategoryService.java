package com.example.demo.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category){
        Optional<Category> categoryOptional = categoryRepository.findCategoryByDesigniation(category.getDesigniation());

        if (categoryOptional.isPresent()){
            throw new IllegalStateException("Category with name "+category.getDesigniation()+" alredy exist");
        }

        categoryRepository.save(category);
    }

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }


}
