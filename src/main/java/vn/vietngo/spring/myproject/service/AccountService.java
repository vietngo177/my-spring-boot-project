package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Account;


public interface AccountService {
    Account getAccountByTenDangNhap(String tenDangNhap);

    void addAccount(Account account);
}
