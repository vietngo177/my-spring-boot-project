package vn.vietngo.spring.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vietngo.spring.myproject.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
