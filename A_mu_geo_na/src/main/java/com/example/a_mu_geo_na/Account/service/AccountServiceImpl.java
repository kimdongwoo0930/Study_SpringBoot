package com.example.a_mu_geo_na.Account.service;

import com.example.a_mu_geo_na.Account.Entity.Account;
import com.example.a_mu_geo_na.Account.Entity.Dto.AccountResponseDto;
import com.example.a_mu_geo_na.Account.Entity.Dto.AccountSignupRequestDto;
import com.example.a_mu_geo_na.Account.config.JwtTokenProvider;
import com.example.a_mu_geo_na.Account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public AccountResponseDto signUp(AccountSignupRequestDto accountSignupRequestDto) throws Exception{
        if(accountRepository.findById(accountSignupRequestDto.getUser_id()).isPresent()){
            return AccountResponseDto.of(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다.");
        }

        if(!accountSignupRequestDto.getUser_password().equals(accountSignupRequestDto.getChecked_password())){
                return AccountResponseDto.of(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        Account account = accountRepository.save(accountSignupRequestDto.toEntity());
        account.encodePassword(passwordEncoder);

        return AccountResponseDto.of(HttpStatus.OK, "회원가입 성공");

    }


    @Override
    public AccountResponseDto login(Map<String, String> accounts){
        Account account = accountRepository.findById(accounts.get("user_id"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 아이디입니다."));

        String password = accounts.get("user_password");
        if(!checkPassword(passwordEncoder, password, account)){
            return AccountResponseDto.of(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다.");
        }
        List<String> roles = new ArrayList<>();
        roles.add(account.getRole().name());

        return AccountResponseDto.of(HttpStatus.OK, "로그인성공",jwtTokenProvider.createToken(account.getUser_id(), roles));

    }



    public Boolean checkPassword(PasswordEncoder passwordEncoder, String password, Account account){
        return passwordEncoder.matches(password, account.getUser_password());
    }


}
