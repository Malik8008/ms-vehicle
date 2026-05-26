package az.msvehicle.service;

import az.msvehicle.dto.vehicle.GetVehicleDTO;
import az.msvehicle.dto.vehicle.PostVehicleDTO;
import az.msvehicle.dto.vehicle.PutVehicleDTO;

import java.util.List;

public interface IVehicleService {
    List<GetVehicleDTO> getVehicles();

    List<GetVehicleDTO> findByUserId(Long userId);

    GetVehicleDTO getById(Long id);

    GetVehicleDTO create(PostVehicleDTO postVehicleDTO);

    GetVehicleDTO update(Long id, PutVehicleDTO putVehicleDTO);

    void delete(Long id);
}
