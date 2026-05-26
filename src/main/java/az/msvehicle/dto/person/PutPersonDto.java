package az.msvehicle.dto.person;

import lombok.Data;

@Data
public class PutPersonDto {
    String name;
    String surname;
    int age;
    String phone;
}
