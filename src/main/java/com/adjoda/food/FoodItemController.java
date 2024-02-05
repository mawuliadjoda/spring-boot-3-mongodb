package com.adjoda.food;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/food-inventory")
@RequiredArgsConstructor
public class FoodItemController {
    private final FoodItemRepository foodItemRepository;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(foodItemRepository.findAll());
    }
    @PostMapping("/")
    public ResponseEntity<FoodItem> saveFoodItem(@RequestBody FoodItem foodItem) {
        return ResponseEntity.ok()
                .body(foodItemRepository.save(foodItem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable String id) {
        var optionalFoodItem = foodItemRepository.findById(id);

        return optionalFoodItem.isEmpty() ?

                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null) :

                ResponseEntity.ok()
                        .body(optionalFoodItem.get());
    }
}
