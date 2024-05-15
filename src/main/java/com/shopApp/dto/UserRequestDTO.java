package kg.edu.alatoo.online.shop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRequestDTO {
    private Long userId;

    @NotBlank(message = "Имя не должно быть пустым")
    private String username;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Неверный формат почты")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, message = "Пароль не должен быть меньше 8 символов")
    private String password;
}
