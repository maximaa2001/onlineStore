package by.bsuir.dao.impl;

import by.bsuir.dao.AuctionDao;
import by.bsuir.entity.domain.Auction;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.User;
import by.bsuir.repo.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class AuctionDaoImpl implements AuctionDao {
    private final AuctionRepo auctionRepo;

    @Autowired
    public AuctionDaoImpl(AuctionRepo auctionRepo){
        this.auctionRepo = auctionRepo;
    }

    @Override
    public Auction save(Auction auction) {
        return auctionRepo.save(auction);
    }

    @Override
    public Optional<Auction> auctionIsExists(Product product) {
        return auctionRepo.findByProductAndEndDateAfter(product, LocalDateTime.now());
    }

    @Override
    public List<Auction> findByUserActual(User user) {
        return auctionRepo.findByUserAndIsActive(user, true);
    }

    @Override
    public Auction findById(Integer id) {
        return auctionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find auction with id " + id));
    }

    @Override
    public List<Auction> findAll() {
        return auctionRepo.findAll();
    }

    @Override
    public Optional<Auction> findActualByProduct(Product product) {
        return auctionRepo.findByProductAndIsActive(product, true);
    }
}
