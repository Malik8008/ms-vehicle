package az.msvehicle.dto.vehicle;

import lombok.Data;

@Data
public class PutVehicleDTO {
    String brand;
    String model;
    Long personId;
}
