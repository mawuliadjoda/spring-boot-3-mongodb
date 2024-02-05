package com.adjoda.food;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
}
