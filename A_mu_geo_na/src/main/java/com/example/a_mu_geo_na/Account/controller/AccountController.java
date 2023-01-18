package com.example.a_mu_geo_na.Account.controller;

import com.example.a_mu_geo_na.Account.Entity.Dto.AccountResponseDto;
import com.example.a_mu_geo_na.Account.Entity.Dto.AccountSignupRequestDto;
import com.example.a_mu_geo_na.Account.repository.AccountRepository;
import com.example.a_mu_geo_na.Account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/account")
@RestController
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponseDto signup(@Validated @RequestBody AccountSignupRequestDto request) throws Exception{
        AccountResponseDto response = accountService.signUp(request);
        return response;
    }

    @PostMapping("/login")
    public AccountResponseDto login(@RequestBody Map<String, String> account){
        AccountResponseDto response = accountService.login(account);
        return response;
    }

}
