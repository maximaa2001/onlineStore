package by.bsuir.entity.domain.key;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RatingKey implements Serializable {
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer setUserId;
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer getUserId;
}
