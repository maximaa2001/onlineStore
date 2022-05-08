package by.bsuir.entity.domain;

import by.bsuir.entity.domain.key.RatingKey;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_rating")
public class UserRating {

    @EmbeddedId
    private RatingKey key;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "rating_number_id")
    private Rating rating;
}
