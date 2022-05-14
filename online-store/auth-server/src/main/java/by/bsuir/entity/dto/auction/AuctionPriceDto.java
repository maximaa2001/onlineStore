package by.bsuir.entity.dto.auction;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuctionPriceDto {
    private Integer auctionId;
    private BigDecimal price;

}
