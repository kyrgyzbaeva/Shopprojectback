package com.shopApp.service;

import com.shopApp.entity.Bag;
import com.shopApp.repository.BagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BagService {

    private final BagRepository bagRepository;

    @Autowired
    public BagService(BagRepository bagRepository) {
        this.bagRepository = bagRepository;
    }

    public List<Bag> getAllBags() {
        return bagRepository.findAll();
    }

    public Optional<Bag> getBagById(Long id) {
        return bagRepository.findById(id);
    }

    public Bag saveBag(Bag bag) {
        return bagRepository.save(bag);
    }

    public void deleteBagById(Long id) {
        bagRepository.deleteById(id);
    }

    public Bag updateBag(Long id, Bag newBag) {
        Bag existingBag = bagRepository.findById(id).orElseThrow();
        existingBag.setBrand(newBag.getBrand());
        existingBag.setPrice(newBag.getPrice());
        existingBag.setProduction(newBag.getProduction());
        existingBag.setMaterial(newBag.getMaterial());
        return bagRepository.save(existingBag);
    }
}

