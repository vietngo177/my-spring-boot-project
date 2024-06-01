package vn.vietngo.spring.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vietngo.spring.myproject.repository.AccountRepository;
import vn.vietngo.spring.myproject.entity.Account;

@Service
public class AccountServiceImpl implements AccountService{
    private AccountRepository accountRepository;

    public AccountServiceImpl() {
    }

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountByTenDangNhap(String tenDangNhap) {
        return accountRepository.findByTenDangNhap(tenDangNhap);
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.saveAndFlush(account);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account getAccountByResetPasswordToken(String token) {
        return accountRepository.findByResetPasswordToken(token);
    }
}
