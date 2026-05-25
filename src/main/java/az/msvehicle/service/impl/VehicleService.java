package az.msvehicle.service.impl;

import az.msvehicle.client.UserClient;
import az.msvehicle.dto.VehicleDTOs.GetVehicleDTO;
import az.msvehicle.dto.VehicleDTOs.PostVehicleDTO;
import az.msvehicle.dto.VehicleDTOs.PutVehicleDTO;
import az.msvehicle.exception.IdNotFoundException;
import az.msvehicle.model.Vehicle;
import az.msvehicle.repository.VehicleRepository;
import az.msvehicle.service.IVehicleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    private final UserClient userClient;

    @Override
    public List<GetVehicleDTO> getVehicles() {
        return vehicleRepository.findAllByDeletedFalse()
                .stream().map(vhc -> modelMapper.map(vhc, GetVehicleDTO.class)).toList();
    }

    @Override
    public List<GetVehicleDTO> findByUserId(Long userId) {
        return vehicleRepository.findByUserId(userId)
                .stream().map(vhcWithUser -> modelMapper.map(vhcWithUser, GetVehicleDTO.class)).toList();
    }

    @Override
    public GetVehicleDTO getById(Long id) {
        Vehicle existVehicle = vehicleRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Vehicle with id:" + id + " not found"));
        return modelMapper.map(existVehicle, GetVehicleDTO.class);
    }

    @Override
    public GetVehicleDTO create(PostVehicleDTO postVehicleDTO) {
        userClient.getUserByIdInternal(postVehicleDTO.getUserId());

        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(postVehicleDTO.getBrand());
        vehicle.setModel(postVehicleDTO.getModel());
        vehicle.setUserId(postVehicleDTO.getUserId());
        vehicle.setDeleted(false);

        Vehicle saveVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(saveVehicle, GetVehicleDTO.class);
    }

    @Override
    public GetVehicleDTO update(Long id, PutVehicleDTO putVehicleDTO) {
        Vehicle existVehicle = vehicleRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Vehicle with id:" + id + " not found"));
        existVehicle.setBrand(putVehicleDTO.getBrand());
        existVehicle.setModel(putVehicleDTO.getModel());
        existVehicle.setUserId(putVehicleDTO.getUserId());

        Vehicle updateVehicle = vehicleRepository.save(existVehicle);

        return modelMapper.map(updateVehicle, GetVehicleDTO.class);
    }

    @Override
    public void delete(Long id) {
        Vehicle existVehicle = vehicleRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Vehicle with:" + id + " not found"));
        existVehicle.setDeleted(true);
        vehicleRepository.save(existVehicle);
    }
}
