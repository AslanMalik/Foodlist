package kz.main.foodlist.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.main.foodlist.entity.Food;
import kz.main.foodlist.repository.CustomFoodRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomFoodRepIMPL implements CustomFoodRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Food> getFoodByPriceMore(Integer price) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Food> query = cb.createQuery(Food.class);
        Root<Food> root = query.from(Food.class);

        Predicate predicate = cb.greaterThanOrEqualTo(root.get("price"), price);
        query.select(root).where(predicate);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Food> getFoodByPriceAndName(Integer price, String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Food> query = cb.createQuery(Food.class);
        Root<Food> root = query.from(Food.class);

        List<Predicate> predicates = new ArrayList<>();

        if(price != null) {
            predicates.add(cb.greaterThan(root.get("price"), price));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        if(name != null && !name.isEmpty()) {
            predicates.add(cb.like(root.get("name"), name));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Food> getFoodBySort() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Food> criteriaQuery = cb.createQuery(Food.class);
        Root<Food> root = criteriaQuery.from(Food.class);

        criteriaQuery.select(root).orderBy(cb.desc(root.get("price")));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
