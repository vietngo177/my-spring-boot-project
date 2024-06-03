package vn.vietngo.spring.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vietngo.spring.myproject.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByTenDangNhap(String tenDangNhap);

    Account findByEmail(String email);

    Account findByResetPasswordToken(String token);
}
