package com.example.demo.Category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CategoryConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            CategoryRepository repository) {
        return args -> {
            Category cat1 = new Category("Netoyage");
            Category cat2 = new Category("Alimentation");
            Category cat3 = new Category("work");

            repository.saveAll(
                    List.of(cat1, cat3, cat2)
            );
        };
    }
}
