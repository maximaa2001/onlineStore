package by.bsuir.dao;

import by.bsuir.entity.domain.BasketItem;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.User;
import by.bsuir.entity.domain.key.BasketId;

import java.util.Optional;

public interface BasketItemDao {
    Optional<BasketItem> findById(BasketId basketId);
    BasketItem save(BasketItem basketItem);
    void removeById(BasketId basketId);
}
