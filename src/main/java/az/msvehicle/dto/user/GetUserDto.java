package az.msvehicle.dto.user;

import lombok.Data;


@Data
public class GetUserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
