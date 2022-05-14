package by.bsuir.entity.dto.auction;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuctionIdDto {
    private Integer auctionId;

    public static AuctionIdDto of(Integer id){
        return AuctionIdDto.builder()
                .auctionId(id)
                .build();
    }
}
