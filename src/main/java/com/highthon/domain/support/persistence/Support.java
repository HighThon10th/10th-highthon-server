package com.highthon.domain.support.persistence;

import com.highthon.domain.product.persistence.Product;
import com.highthon.domain.user.persistence.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_support")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Support {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "support_id", nullable = false)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
