package az.msvehicle.service;

import az.msvehicle.dto.VehicleDTOs.GetVehicleDTO;
import az.msvehicle.dto.VehicleDTOs.PostVehicleDTO;
import az.msvehicle.dto.VehicleDTOs.PutVehicleDTO;

import java.util.List;

public interface IVehicleService {
    List<GetVehicleDTO> getVehicles();
    List<GetVehicleDTO> findByUserId(Long userId);
    GetVehicleDTO getById(Long id);
    GetVehicleDTO create(PostVehicleDTO postVehicleDTO);
    GetVehicleDTO update(Long id, PutVehicleDTO putVehicleDTO);
    void delete(Long id);
}
