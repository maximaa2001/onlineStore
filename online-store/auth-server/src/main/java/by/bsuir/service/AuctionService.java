package by.bsuir.service;

import by.bsuir.entity.dto.ResultDto;
import by.bsuir.entity.dto.auction.AuctionIdDto;
import by.bsuir.entity.dto.auction.AuctionPriceDto;
import by.bsuir.entity.dto.auction.AuctionStateDto;
import by.bsuir.entity.dto.product.ProductIdDto;

public interface AuctionService {
    AuctionIdDto createAuction(Integer userId, ProductIdDto productIdDto);
    AuctionStateDto getAuctionState(Integer userId, Integer auctionId);
    AuctionStateDto getAuction(Integer userId, Integer auctionId);
    ResultDto takePart(Integer userId, AuctionPriceDto auctionPriceDto);
}
