package com.highthon.domain.category.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    private String name;

    private Integer fundingCount;

    public void addFunding() {
        this.fundingCount++;
    }

}
