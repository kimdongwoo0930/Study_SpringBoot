package com.example.a_mu_geo_na.domain.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountSignupRequestDto {

    private String user_id;
    private String user_password;

    private String checked_password;
    private String nickname;

    private Role role;

    @Builder
    public Account toEntity(){
        return Account.builder()
                .user_id(user_id)
                .nickname(nickname)
                .user_password(user_password)
                .role(Role.USER)
                .build();
    }
}
