package com.shopApp.DTO.mapper;

import com.shopApp.DTO.response.BagDTO;
import com.shopApp.entity.Bag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BagMapper {

    BagMapper INSTANCE = Mappers.getMapper(BagMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "production", target = "production")
    @Mapping(source = "material", target = "material")
    BagDTO bagToBagDTO(Bag bag);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "brand", target = "brand")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "production", target = "production")
    @Mapping(source = "material", target = "material")
    Bag bagDTOToBag(BagDTO bagDTO);
}
