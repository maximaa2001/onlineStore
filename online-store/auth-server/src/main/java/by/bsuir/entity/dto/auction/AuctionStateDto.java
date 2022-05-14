package by.bsuir.entity.dto.auction;

import by.bsuir.entity.domain.Auction;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuctionStateDto {
    private Integer productId;
    private Integer userId;
    private String email;
    private Long endDate;
    private BigDecimal currentPrice;

    public static AuctionStateDto of(Auction auction){
        return AuctionStateDto.builder()
                .productId(auction.getProduct().getProductId())
                .userId(auction.getUser().getUserId())
                .email(auction.getUser().getUserEmail())
                .endDate(Timestamp.valueOf(auction.getEndDate()).getTime())
                .currentPrice(auction.getCurrentPrice())
                .build();
    }
}
