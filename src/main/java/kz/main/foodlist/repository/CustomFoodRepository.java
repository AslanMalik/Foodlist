package kz.main.foodlist.repository;

import kz.main.foodlist.entity.Food;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomFoodRepository {
    List<Food> getFoodByPriceMore(Integer price);

    List<Food> getFoodByPriceAndName(Integer price, String name);

    List<Food> getFoodBySort();
}
