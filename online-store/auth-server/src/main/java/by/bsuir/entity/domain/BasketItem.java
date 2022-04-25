package by.bsuir.entity.domain;

import by.bsuir.entity.domain.key.BasketId;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "basket")
public class BasketItem {
    @EmbeddedId
    @EqualsAndHashCode.Include
    private BasketId basketId;

}
