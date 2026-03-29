package kz.main.foodlist.repository;

import kz.main.foodlist.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManRepository extends JpaRepository<Manufacturer, Integer> {
}
