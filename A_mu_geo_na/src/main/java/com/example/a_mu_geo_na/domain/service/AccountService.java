package com.example.a_mu_geo_na.domain.service;

import com.example.a_mu_geo_na.domain.Account.AccountSignupRequestDto;

import java.util.Map;

public interface AccountService {

    //회원가입하기
    public String signUp(AccountSignupRequestDto accountSignupRequestDto) throws Exception;

    public String login(Map<String, String> accounts);
}
