package by.bsuir.repo;

import by.bsuir.entity.domain.BasketItem;
import by.bsuir.entity.domain.key.BasketId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketItemRepo extends JpaRepository<BasketItem, BasketId> {
    Optional<BasketItem> findByBasketId(BasketId basketId);
}
