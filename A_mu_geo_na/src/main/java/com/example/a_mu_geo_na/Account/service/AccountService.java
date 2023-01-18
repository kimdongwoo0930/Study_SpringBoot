package com.example.a_mu_geo_na.Account.service;

import com.example.a_mu_geo_na.Account.Entity.Dto.AccountResponseDto;
import com.example.a_mu_geo_na.Account.Entity.Dto.AccountSignupRequestDto;

import java.util.Map;

public interface AccountService {

    //회원가입하기
    public AccountResponseDto signUp(AccountSignupRequestDto accountSignupRequestDto) throws Exception;

    public AccountResponseDto login(Map<String, String> accounts);
}
