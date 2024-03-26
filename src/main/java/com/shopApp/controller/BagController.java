package com.shopApp.controller;

import com.shopApp.DTO.response.BagDTO;
import com.shopApp.service.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bags")
public class BagController {

    private final BagService bagService;

    @Autowired
    public BagController(BagService bagService) {
        this.bagService = bagService;
    }

    @GetMapping
    public ResponseEntity<List<BagDTO>> getAllBags() {
        List<BagDTO> bagDTOs = bagService.getAllBagDTOs();
        return ResponseEntity.ok(bagDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BagDTO> getBagById(@PathVariable Long id) {
        return bagService.getBagDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BagDTO> createBag(@RequestBody BagDTO bagDTO) {
        BagDTO createdBagDTO = bagService.saveBagDTO(bagDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBagDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BagDTO> updateBag(@PathVariable Long id, @RequestBody BagDTO newBagDTO) {
        return ResponseEntity.ok(bagService.updateBagDTO(id, newBagDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBag(@PathVariable Long id) {
        bagService.deleteBagById(id);
        return ResponseEntity.noContent().build();
    }
}
