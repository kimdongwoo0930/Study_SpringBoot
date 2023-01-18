package com.example.a_mu_geo_na.Account.Entity.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Builder
public class AccountResponseDto {
    private HttpStatus status;
    private String message;
    private String Token;


    private AccountResponseDto(HttpStatus status,String message, String token) {
        this.status = status;
        this.message = message;
        this.Token = token;
    }

    public static AccountResponseDto of(HttpStatus status,String message, String token) {
        return new AccountResponseDto(status, message,token);
    }
    public static AccountResponseDto of(HttpStatus status, String message){
        return new AccountResponseDto(status,message,null);
    }


}
