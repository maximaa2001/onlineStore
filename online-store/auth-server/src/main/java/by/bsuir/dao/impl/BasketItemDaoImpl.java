package by.bsuir.dao.impl;

import by.bsuir.dao.BasketItemDao;
import by.bsuir.entity.domain.BasketItem;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.User;
import by.bsuir.entity.domain.key.BasketId;
import by.bsuir.repo.BasketItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BasketItemDaoImpl implements BasketItemDao {
    private BasketItemRepo basketItemRepo;

    @Autowired
    public BasketItemDaoImpl(BasketItemRepo basketItemRepo){
        this.basketItemRepo = basketItemRepo;
    }

    @Override
    public Optional<BasketItem> findById(BasketId basketId) {
        return basketItemRepo.findById(basketId);
    }

    @Override
    public BasketItem save(BasketItem basketItem) {
        return basketItemRepo.save(basketItem);
    }

    @Override
    public void removeById(BasketId basketId) {
        basketItemRepo.deleteById(basketId);
    }
}
