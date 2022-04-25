package by.bsuir.entity.dto.product.edit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditProductDto {
    private Integer id;
    private String productName;
    private String description;
    private String category;
    private String city;
    private BigDecimal price;
}