package com.example.a_mu_geo_na.domain.repository;

import com.example.a_mu_geo_na.domain.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    
}
