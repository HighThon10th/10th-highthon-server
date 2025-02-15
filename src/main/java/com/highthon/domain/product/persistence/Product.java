package com.highthon.domain.product.persistence;

import com.highthon.domain.funding.persistence.Funding;
import com.highthon.domain.support.persistence.Support;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_product")
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

    private Long price;

    private Long quantity;

    @OneToMany(mappedBy = "product")
    private List<Support> supports = new ArrayList<>();

    @JoinColumn(name = "funding_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Funding funding;

}
