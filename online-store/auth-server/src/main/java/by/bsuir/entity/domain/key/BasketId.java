package by.bsuir.entity.domain.key;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BasketId implements Serializable {

    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private Integer user;

    @Column(name = "product_id")
    @EqualsAndHashCode.Include
    private Integer product;


}