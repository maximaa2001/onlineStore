package by.bsuir.dao;

import by.bsuir.entity.domain.Auction;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.User;

import java.util.List;
import java.util.Optional;

public interface AuctionDao {
    Auction save(Auction auction);
    void saveAll(List<Auction> auctions);
    Optional<Auction> auctionIsExists(Product product);
    List<Auction> findByUserActual(User user);
    Auction findById(Integer id);
    List<Auction> findAllActive();
    Optional<Auction> findActualByProduct(Product product);
    List<Auction> findExpiredAuction();
}
