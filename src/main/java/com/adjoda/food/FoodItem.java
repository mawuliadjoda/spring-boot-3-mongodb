package com.adjoda.food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "food-items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
    @Id
    private String id;
    private String name;
    private int quantity;

    @DocumentReference(collection = "food-categories")
    private FoodCategory foodCategory;
}