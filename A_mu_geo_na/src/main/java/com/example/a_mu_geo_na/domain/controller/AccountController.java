package com.example.a_mu_geo_na.domain.controller;

import com.example.a_mu_geo_na.domain.Account.AccountSignupRequestDto;
import com.example.a_mu_geo_na.domain.repository.AccountRepository;
import com.example.a_mu_geo_na.domain.service.AccountService;
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
    public String signup(@Validated @RequestBody AccountSignupRequestDto request) throws Exception{
        return accountService.signUp(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> account){
        return accountService.login(account);
    }

}
