package kg.edu.alatoo.online.shop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BagRequestDTO {
    private Long id;
    @NotNull
    private String brand;
    @NotNull
    private double price;
    private String production;
    private String material;
}
