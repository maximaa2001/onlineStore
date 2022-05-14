package by.bsuir.service.schedule;

import by.bsuir.dao.AuctionDao;
import by.bsuir.dao.TokenDao;
import by.bsuir.entity.domain.Auction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class ExpirationAuctionExecutor {

    private final AuctionDao auctionDao;

    public ExpirationAuctionExecutor(AuctionDao auctionDao){
        this.auctionDao = auctionDao;
    }

    @Transactional
   // @Scheduled(fixedDelay = 300000)
    public void closeAuction(){
        List<Auction> expiredAuction = auctionDao.findExpiredAuction();
        expiredAuction.forEach(auction -> {
            auction.setIsActive(false);
        });
        auctionDao.saveAll(expiredAuction);
    }
}
