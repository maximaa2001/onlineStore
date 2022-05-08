package by.bsuir.entity.domain.key;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RatingKey implements Serializable {
    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "set_user_id")
    private Integer setUserId;
    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "get_user_id")
    private Integer getUserId;
}
