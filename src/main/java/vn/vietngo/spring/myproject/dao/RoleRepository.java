package vn.vietngo.spring.myproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vietngo.spring.myproject.entity.Role;

import java.util.ArrayList;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRole(String role);
}
