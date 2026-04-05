package kz.main.foodlist.repository;

import kz.main.foodlist.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query("SELECT f FROM Food f WHERE f.name " +
            "ILIKE concat('%', :search, '%')" +
            "OR f.manufacturer.name ILIKE concat('%', :search, '%')")
    List<Food> getFoodList(String search);

    Page<Food> findAll(Pageable pageable);

    Page<Food> findByPriceGreaterThan(int price, Pageable pageable);
}
