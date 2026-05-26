package az.msvehicle.dto.vehicle;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetVehicleDTO {
    Long id;
    String brand;
    String model;
    LocalDateTime createdAt;
    //Long userId;
}
