package kg.edu.alatoo.online.shop.service;

import kg.edu.alatoo.online.shop.model.User;
import kg.edu.alatoo.online.shop.dto.BagRequestDTO;
import kg.edu.alatoo.online.shop.dto.BagResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BagService {
    BagResponseDTO create(BagRequestDTO itemRequestDTO, User user);
    BagResponseDTO update(Long id, BagRequestDTO itemRequestDTO, User user);
    BagResponseDTO get(Long id, User user);
    List<BagResponseDTO> getAll(User user);
    void delete(Long id);
}
