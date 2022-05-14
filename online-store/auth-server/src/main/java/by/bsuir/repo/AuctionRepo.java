package by.bsuir.repo;

import by.bsuir.entity.domain.Auction;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuctionRepo extends JpaRepository<Auction, Integer> {
    Optional<Auction> findByProductAndEndDateAfter(Product product, LocalDateTime currentTime);
    List<Auction> findByUserAndIsActive(User user, Boolean isActive);
    Optional<Auction> findByProductAndIsActive(Product product, Boolean isActive);
    List<Auction> findByEndDateBefore(LocalDateTime localDateTime);
    List<Auction> findByIsActive(Boolean isActive);
}
