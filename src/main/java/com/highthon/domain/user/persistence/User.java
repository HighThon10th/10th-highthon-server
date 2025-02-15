package com.highthon.domain.user.persistence;

import com.highthon.domain.user.persistence.type.Authority;
import com.highthon.domain.user.persistence.type.Sex;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(100)")
    private String loginId;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(nullable = false, columnDefinition = "VARCHAR(30)")
    private String name;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Integer age;

    private String businessRegistrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Authority authority;

    public void applyCreator(
            String businessRegistrationNumber
    ) {
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.authority = Authority.CREATOR;
    }

}
