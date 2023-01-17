package com.example.a_mu_geo_na.domain.service;

import com.example.a_mu_geo_na.domain.Account.Account;
import com.example.a_mu_geo_na.domain.Account.AccountSignupRequestDto;
import com.example.a_mu_geo_na.domain.config.JwtTokenProvider;
import com.example.a_mu_geo_na.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountServiceImpl implements  AccountService{
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    @Override
    public String signUp(AccountSignupRequestDto accountSignupRequestDto) throws Exception{

        if(accountRepository.findById(accountSignupRequestDto.getUser_id()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        if(!accountSignupRequestDto.getUser_password().equals(accountSignupRequestDto.getChecked_password())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        Account account = accountRepository.save(accountSignupRequestDto.toEntity());
        account.encodePassword(passwordEncoder);


        return account.getUser_id();
    }


    @Override
    public String login(Map<String, String> accounts){
        Account account = accountRepository.findById(accounts.get("user_id"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디입니다."));

        String password = accounts.get("user_password");
//        if(!account.checkPassword(passwordEncoder, password)){
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
        List<String> roles = new ArrayList<>();
        roles.add(account.getRole().name());

        return jwtTokenProvider.createToken(account.getUser_id(), roles);
    }


}
