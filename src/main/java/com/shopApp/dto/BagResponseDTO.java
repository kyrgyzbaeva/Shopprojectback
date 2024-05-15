package kg.edu.alatoo.online.shop.dto;

import lombok.Data;

@Data
public class BagResponseDTO {
    private Long id;
    private String name;
    private String description;
    private UserResponseDTO user;
}
