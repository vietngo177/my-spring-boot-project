package vn.vietngo.spring.myproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vietngo.spring.myproject.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByTenDangNhap(String tenDangNhap);
}
