package com.shopApp;

import com.shopApp.DTO.mapper.BagMapper;
import com.shopApp.entity.Bag;
import com.shopApp.DTO.response.BagDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class BagMapperTest {

    private final BagMapper bagMapper = Mappers.getMapper(BagMapper.class);

    @Test
    public void testBagToBagDTO() {
        Bag bag = new Bag();
        bag.setId(1L);
        bag.setBrand("Brand1");
        bag.setPrice(100.0);
        bag.setProduction("China");
        bag.setMaterial("Leather");

        BagDTO bagDTO = bagMapper.bagToBagDTO(bag);

        assertThat(bagDTO).isNotNull();
        assertThat(bagDTO.getId()).isEqualTo(bag.getId());
        assertThat(bagDTO.getBrand()).isEqualTo(bag.getBrand());
        assertThat(bagDTO.getPrice()).isEqualTo(bag.getPrice());
        assertThat(bagDTO.getProduction()).isEqualTo(bag.getProduction());
        assertThat(bagDTO.getMaterial()).isEqualTo(bag.getMaterial());
    }

}

