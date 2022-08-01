package com.codingbytime.zexfora.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codingbytime.zexfora.entity.table.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);

    boolean existsByName(String name);
}
