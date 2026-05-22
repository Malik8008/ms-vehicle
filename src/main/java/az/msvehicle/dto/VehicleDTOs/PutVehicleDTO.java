package az.msvehicle.dto.VehicleDTOs;

import lombok.Data;

@Data
public class PutVehicleDTO {
    String brand;
    String model;
    Long userId;
}
