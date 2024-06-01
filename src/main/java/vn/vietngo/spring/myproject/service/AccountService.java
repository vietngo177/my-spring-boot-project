package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Account;


public interface AccountService {
    Account getAccountByTenDangNhap(String tenDangNhap);

    Account getAccountByEmail(String email);

    Account getAccountByResetPasswordToken(String token);

    void addAccount(Account account);

    void updateAccount(Account account);
}
