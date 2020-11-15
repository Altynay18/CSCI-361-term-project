package com.example.demo.data;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

	Category findByCategory(String string);
}
