package com.kodnest.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.bankingapp.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}