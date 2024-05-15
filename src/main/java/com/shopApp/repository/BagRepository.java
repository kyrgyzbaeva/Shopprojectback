package kg.edu.alatoo.online.shop.repository;

import kg.edu.alatoo.online.shop.model.Bag;
import kg.edu.alatoo.online.shop.model.User;
import kg.edu.alatoo.online.shop.dto.BagResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {
    List<BagResponseDTO> findByUser(User user);
}
