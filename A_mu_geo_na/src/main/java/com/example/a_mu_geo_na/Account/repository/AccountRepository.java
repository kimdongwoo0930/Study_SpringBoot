package com.example.a_mu_geo_na.Account.repository;

import com.example.a_mu_geo_na.Account.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    
}
