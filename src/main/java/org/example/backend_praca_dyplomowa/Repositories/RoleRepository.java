package org.example.backend_praca_dyplomowa.Repositories;

import org.example.backend_praca_dyplomowa.Entity.Role;
import org.example.backend_praca_dyplomowa.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
