package com.shopApp.service;

import com.shopApp.DTO.response.BagDTO;
import com.shopApp.DTO.mapper.BagMapper;
import com.shopApp.entity.Bag;
import com.shopApp.repository.BagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BagService {

    private final BagRepository bagRepository;
    private final BagMapper bagMapper;

    @Autowired
    public BagService(BagRepository bagRepository, BagMapper bagMapper) {
        this.bagRepository = bagRepository;
        this.bagMapper = bagMapper;
    }

    public List<BagDTO> getAllBagDTOs() {
        List<Bag> bags = bagRepository.findAll();
        return bags.stream().map(bagMapper::bagToBagDTO).collect(Collectors.toList());
    }

    public Optional<BagDTO> getBagDTOById(Long id) {
        return bagRepository.findById(id).map(bagMapper::bagToBagDTO);
    }

    public BagDTO saveBagDTO(BagDTO bagDTO) {
        Bag bag = bagMapper.bagDTOToBag(bagDTO);
        return bagMapper.bagToBagDTO(bagRepository.save(bag));
    }

    public void deleteBagById(Long id) {
        bagRepository.deleteById(id);
    }

    public BagDTO updateBagDTO(Long id, BagDTO newBagDTO) {
        Bag existingBag = bagMapper.bagDTOToBag(newBagDTO);
        existingBag.setId(id);
        return bagMapper.bagToBagDTO(bagRepository.save(existingBag));
    }
}