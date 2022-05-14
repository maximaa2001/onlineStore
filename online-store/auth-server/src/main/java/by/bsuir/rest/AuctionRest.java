package by.bsuir.rest;

import by.bsuir.entity.dto.ResultDto;
import by.bsuir.entity.dto.auction.AuctionIdDto;
import by.bsuir.entity.dto.auction.AuctionPriceDto;
import by.bsuir.entity.dto.auction.AuctionStateDto;
import by.bsuir.entity.dto.product.ProductIdDto;
import by.bsuir.service.AuctionService;
import by.bsuir.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static by.bsuir.constant.ApiPath.*;

@RestController
@Slf4j
public class AuctionRest {
    private final AuctionService auctionService;
    private final AuthService authService;

    public AuctionRest(AuctionService auctionService, AuthService authService){
        this.auctionService = auctionService;
        this.authService = authService;
    }

    @PostMapping(CREATE_AUCTION)
    public AuctionIdDto createAuction(@RequestHeader(AUTHORIZATION) String token,
                                      @RequestBody ProductIdDto productIdDto){
        return auctionService.createAuction(authService.getUserIdByToken(token), productIdDto);
    }

    @GetMapping(GET_STATE_MY_AUCTION)
    public AuctionStateDto getAuctionState(@RequestHeader(AUTHORIZATION) String token,
                                           @RequestParam(name = "id") Integer auctionId){
        return auctionService.getAuctionState(authService.getUserIdByToken(token), auctionId);
    }

    @GetMapping(GET_STATE_AUCTION)
    public AuctionStateDto getAuction(@RequestHeader(AUTHORIZATION) String token,
                                           @RequestParam(name = "id") Integer auctionId){
        return auctionService.getAuction(authService.getUserIdByToken(token), auctionId);
    }
    @PostMapping(TAKE_PART_AUCTION)
    public ResultDto takePartAuction(@RequestHeader(AUTHORIZATION) String token,
                                      @RequestBody AuctionPriceDto auctionPriceDto){
        return auctionService.takePart(authService.getUserIdByToken(token), auctionPriceDto);
    }

}
