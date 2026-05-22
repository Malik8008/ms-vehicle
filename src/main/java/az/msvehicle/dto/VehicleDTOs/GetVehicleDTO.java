package az.msvehicle.dto.VehicleDTOs;

import lombok.Data;

@Data
public class GetVehicleDTO {
    Long id;
    String brand;
    String model;
    boolean deleted;
    Long userId;
}
