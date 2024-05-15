package kg.edu.alatoo.online.shop.repository;

import kg.edu.alatoo.online.shop.model.Role;
import kg.edu.alatoo.online.shop.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(Roles name);
}
