package com.example.a_mu_geo_na.domain.Account;


import com.example.a_mu_geo_na.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Account extends BaseTimeEntity {
    @Id @Column(name = "user_id", length = 50)
    private String user_id;

    @Setter @Column(length = 100, nullable = false)
    private String user_password;

    @Setter @Column(length = 45)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.user_password = passwordEncoder.encode(user_password);
    }

}
