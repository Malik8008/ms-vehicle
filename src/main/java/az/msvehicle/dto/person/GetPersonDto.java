package az.msvehicle.dto.person;


import az.msvehicle.dto.vehicle.GetVehicleDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetPersonDto {
    Long id;
    String name;
    String surname;
    int age;
    String phone;
    LocalDateTime createdAt;
    List<GetVehicleDTO> vehicles;
}
