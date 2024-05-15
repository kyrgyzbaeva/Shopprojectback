package kg.edu.alatoo.online.shop.service.impl;

import kg.edu.alatoo.online.shop.model.Bag;
import kg.edu.alatoo.online.shop.model.User;
import kg.edu.alatoo.online.shop.exception.BagNotFoundException;
import kg.edu.alatoo.online.shop.mapper.BagMapper;
import kg.edu.alatoo.online.shop.dto.BagRequestDTO;
import kg.edu.alatoo.online.shop.dto.BagResponseDTO;
import kg.edu.alatoo.online.shop.repository.BagRepository;
import kg.edu.alatoo.online.shop.service.BagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BagServiceImpl implements BagService {

    private final BagRepository bagRepository;

    private final BagMapper bagMapper;

    public BagServiceImpl(BagRepository bagRepository, BagMapper bagMapper) {
        this.bagRepository = bagRepository;
        this.bagMapper = bagMapper;
    }

    @Override
    public BagResponseDTO create(BagRequestDTO bagRequestDTO, User user) {
        Bag item = bagMapper.toBag(bagRequestDTO);
        item.setUser(user);
        return bagMapper.toBagResponseDTO(bagRepository.save(item));
    }

    @Override
    public BagResponseDTO update(Long id, BagRequestDTO bagRequestDTO, User user) {
        return bagMapper.toBagResponseDTO(bagRepository.save(bagMapper.toBag(bagRequestDTO)));
    }

    @Override
    public BagResponseDTO get(Long id, User user) {
        return bagMapper.toBagResponseDTO(bagRepository.findById(id).orElseThrow(() -> new BagNotFoundException("Вещи с таким id не существует")));
    }

    @Override
    public List<BagResponseDTO> getAll(User user) {
       return bagRepository.findByUser(user);
    }

    @Override
    public void delete(Long id) {
        bagRepository.deleteById(id);
    }
}
