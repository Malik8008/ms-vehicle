package az.msvehicle.dto.vehicle;

import lombok.Data;

@Data
public class PostVehicleDTO {
    String brand;
    String model;
    //Long userId;
    Long personId;
}
