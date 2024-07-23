package com.kodnest.bankingapp.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.bankingapp.dto.AccountDto;
import com.kodnest.bankingapp.entity.Account;
import com.kodnest.bankingapp.mapper.AccountMapper;
import com.kodnest.bankingapp.repository.AccountRepository;
import com.kodnest.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(
				() -> new RuntimeException("Account does not exists")
				);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(
				() -> new RuntimeException("Account does not exists")
				);
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(
				() -> new RuntimeException("Account does not exists")
				);
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		
		double total = account.getBalance() - amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map(
				(account) -> AccountMapper.mapToAccountDto(account))
				.collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(
				() -> new RuntimeException("Account does not exists")
				);
		accountRepository.deleteById(id);
	}
}