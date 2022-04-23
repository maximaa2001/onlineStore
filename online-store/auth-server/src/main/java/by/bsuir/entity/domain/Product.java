package by.bsuir.entity.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer productId;

    @NotNull
    @ToString.Include
    @Column(name = "product_name")
    private String productName;

    @Column(name = "description")
    @ToString.Include
    private String description;

    @Column(name = "product_price")
    @ToString.Include
    private BigDecimal price;

    @NotNull
    @ToString.Include
    @Column(name = "actual_date")
    private LocalDateTime actualDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Include
    @JoinColumn(name = "product_status_id", referencedColumnName = "product_status_id")
    private ProductStatus productStatus;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Include
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Include
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Include
    @JoinColumn(name = "product_level_id", referencedColumnName = "product_level_id")
    private ProductLevel productLevel;

    @OneToMany(mappedBy = "product")
    private Set<ProductImage> images;
}
