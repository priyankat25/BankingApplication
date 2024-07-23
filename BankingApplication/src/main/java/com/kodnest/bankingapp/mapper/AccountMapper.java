package com.kodnest.bankingapp.mapper;

import com.kodnest.bankingapp.dto.AccountDto;
import com.kodnest.bankingapp.entity.Account;

public class AccountMapper {
	
//	Map Account to AccountDto
	public static Account mapToAccount(AccountDto accountDto) {
		Account account = new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
				);	
		return account;
	}
	
//	Map AccountDto to Account
	public static AccountDto mapToAccountDto(Account account) {
		AccountDto accountDto = new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				);
		return accountDto;
	}
}