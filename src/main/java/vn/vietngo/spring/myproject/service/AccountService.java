package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Account;

import java.util.List;
import java.util.Optional;


public interface AccountService {
    List<Account> getAllAccount();

    Account getAccountByTenDangNhap(String tenDangNhap);

    Account getAccountByEmail(String email);

    Account getAccountByResetPasswordToken(String token);

    void addAccount(Account account);

    void updateAccount(Account account);

    void deleteAccountById(Long id);

    Optional<Account> getAccountById(Long id);


}
