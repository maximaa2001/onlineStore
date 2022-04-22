package by.bsuir.entity.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    @ToString.Include
    @EqualsAndHashCode.Include
    private Integer productImageId;

    @NotNull
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;
}
