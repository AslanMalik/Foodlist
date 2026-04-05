package kz.main.foodlist.service;

import kz.main.foodlist.entity.Food;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FoodService {

    List<Food> getFoodByAscending();

    List<Food> getAllFoodByWord(String word);

    Food getFoodById(int id);

    void deleteById(int id);

    void addFood(Food food);

    void updateFood(Food food);

    @Nullable Object getFoodByPriceMore(Integer price);

    @Nullable Object getFoodByPriceAndName(Integer price, String name);

    @Nullable Object getFoodBySort();

    Page<Food> findAllPagination(Pageable pageable);

    Page<Food> findByPriceGreaterThan(int price, Pageable pageable);
}
