package kg.edu.alatoo.online.shop.repository;

import kg.edu.alatoo.online.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);
    User findByEmailIgnoreCase(String email);

    Optional<User> findByUsername(String name);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
