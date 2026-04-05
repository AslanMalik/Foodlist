package kz.main.foodlist.service.impl;

import kz.main.foodlist.entity.Food;
import kz.main.foodlist.repository.CustomFoodRepository;
import kz.main.foodlist.repository.FoodRepository;
import kz.main.foodlist.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("MAIN")
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final CustomFoodRepository customFoodRepository;

    @Override
    public List<Food> getFoodByAscending() {
        return foodRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Food> getAllFoodByWord(String word) {
        return foodRepository.getFoodList(word);
    }

    @Override
    public Food getFoodById(int id) {
        return foodRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        foodRepository.deleteById(id);
    }

    @Override
    public void addFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void updateFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public @Nullable Object getFoodByPriceMore(Integer price) {
        return customFoodRepository.getFoodByPriceMore(price);
    }

    @Override
    public @Nullable Object getFoodByPriceAndName(Integer price, String name) {
        return customFoodRepository.getFoodByPriceAndName(price, name);
    }

    @Override
    public @Nullable Object getFoodBySort() {
        return customFoodRepository.getFoodBySort();
    }

    @Override
    public Page<Food> findAllPagination(Pageable pageable) {
        return foodRepository.findAll(pageable);
    }

    @Override
    public Page<Food> findByPriceGreaterThan(int price, Pageable pageable) {
        return foodRepository.findByPriceGreaterThan(price, pageable);
    }
}
