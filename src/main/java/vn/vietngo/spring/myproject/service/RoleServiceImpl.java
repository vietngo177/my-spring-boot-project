package vn.vietngo.spring.myproject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vietngo.spring.myproject.repository.RoleRepository;
import vn.vietngo.spring.myproject.entity.Role;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    private RoleRepository roleRepository;

    public RoleServiceImpl() {
    }

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
}
