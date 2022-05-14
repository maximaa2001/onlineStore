package by.bsuir.service.impl;

import by.bsuir.dao.AuctionDao;
import by.bsuir.dao.ProductDao;
import by.bsuir.dao.RefDao;
import by.bsuir.dao.UserDao;
import by.bsuir.entity.domain.Auction;
import by.bsuir.entity.domain.Product;
import by.bsuir.entity.domain.User;
import by.bsuir.entity.dto.ResultDto;
import by.bsuir.entity.dto.auction.AuctionIdDto;
import by.bsuir.entity.dto.auction.AuctionPriceDto;
import by.bsuir.entity.dto.auction.AuctionStateDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.exception.AuctionAlsoExistsException;
import by.bsuir.exception.AuctionIsNotLinkedException;
import by.bsuir.exception.ProductIsNotLinkedException;
import by.bsuir.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionDao auctionDao;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final RefDao refDao;

    @Value("${auction.days}")
    private Integer daysForAuction;

    @Autowired
    public AuctionServiceImpl(AuctionDao auctionDao, ProductDao productDao, UserDao userDao, RefDao refDao){
        this.auctionDao = auctionDao;
        this.productDao = productDao;
        this.userDao = userDao;
        this.refDao = refDao;
    }

    @Override
    @Transactional
    public AuctionIdDto createAuction(Integer userId, ProductIdDto productIdDto) {
        Product product = productDao.findById(productIdDto.getProductId());
        User user = userDao.findById(userId);
        Optional<Auction> auction = auctionDao.auctionIsExists(product);
        if(auction.isPresent()){
            throw new AuctionAlsoExistsException(HttpStatus.BAD_REQUEST);
        }
        List<Product> allProductsByUser = productDao.findProductsByUserAndStatus(user, refDao.findApprovedStatus());
        if(!allProductsByUser.contains(product)){
            throw new ProductIsNotLinkedException(HttpStatus.BAD_REQUEST);
        }
        Auction savedAuction = auctionDao.save(Auction.builder()
                .product(product)
                .user(user)
                .currentPrice(BigDecimal.ZERO)
                .endDate(LocalDateTime.now().plusDays(daysForAuction))
                .isActive(true)
                .build());
        return AuctionIdDto.of(savedAuction.getAuctionId());
    }

    @Override
    public AuctionStateDto getAuctionState(Integer userId, Integer auctionId) {
        Auction auction = auctionDao.findById(auctionId);
        if(!auction.getProduct().getUser().getUserId().equals(userId)){
            throw new AuctionIsNotLinkedException(HttpStatus.BAD_REQUEST);
        }
        return AuctionStateDto.of(auction);
    }

    @Override
    public AuctionStateDto getAuction(Integer userId, Integer auctionId) {
        Auction auction = auctionDao.findById(auctionId);
        if(auction.getProduct().getUser().getUserId().equals(userId) || !auction.getIsActive()){
            throw new AuctionIsNotLinkedException(HttpStatus.BAD_REQUEST);
        }
        return AuctionStateDto.of(auction);
    }

    @Override
    public ResultDto takePart(Integer userId, AuctionPriceDto auctionPriceDto) {
        Auction auction = auctionDao.findById(auctionPriceDto.getAuctionId());
        if(auction.getEndDate().isBefore(LocalDateTime.now()) || auction.getCurrentPrice().compareTo(auctionPriceDto.getPrice()) >= 0){
            return ResultDto.builder()
                    .isSuccess(false)
                    .build();
        }
        User user = userDao.findById(userId);
        auction.setCurrentPrice(auctionPriceDto.getPrice());
        auction.setUser(user);
        auctionDao.save(auction);
        return ResultDto.builder()
                .isSuccess(true)
                .build();
    }

}
