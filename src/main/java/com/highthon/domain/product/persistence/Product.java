package com.highthon.domain.product.persistence;

import com.highthon.domain.funding.persistence.Funding;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_funding")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    private String name;

    private String thumbnailImgUrl;

    private String description;

    private Long price;

    private Long quantity;

    @JoinColumn(name = "funding_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Funding funding;

}
