package az.msvehicle.service.impl;

import az.msvehicle.client.UserClient;
import az.msvehicle.dto.vehicle.GetVehicleDTO;
import az.msvehicle.dto.vehicle.PostVehicleDTO;
import az.msvehicle.dto.vehicle.PutVehicleDTO;
import az.msvehicle.entity.Person;
import az.msvehicle.exception.IdNotFoundException;
import az.msvehicle.entity.Vehicle;
import az.msvehicle.repository.PersonRepository;
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
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    //private final UserClient userClient;

    @Override
    public List<GetVehicleDTO> getVehicles() {
        return vehicleRepository.findAllByIsDeletedFalse()
                .stream().map(vhc -> modelMapper.map(vhc, GetVehicleDTO.class)).toList();
    }

    @Override
    public List<GetVehicleDTO> findByUserId(Long userId) {
        return vehicleRepository.findByUserId(userId)
                .stream().map(vhcWithUser -> modelMapper.map(vhcWithUser, GetVehicleDTO.class)).toList();
    }

    @Override
    public GetVehicleDTO getById(Long id) {
        Vehicle existVehicle = vehicleRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Vehicle with id:" + id + " not found"));
        return modelMapper.map(existVehicle, GetVehicleDTO.class);
    }

    @Override
    public GetVehicleDTO create(PostVehicleDTO postVehicleDTO) {
        //userClient.getUserByIdInternal(postVehicleDTO.getUserId());

        Person person = personRepository.findByIdAndIsDeletedFalse(postVehicleDTO.getPersonId())
                .orElseThrow(()->
                        new IdNotFoundException("Person with id:" + postVehicleDTO.getPersonId() + " not found"));
        Vehicle vehicle = new Vehicle();

        vehicle.setBrand(postVehicleDTO.getBrand());
        vehicle.setModel(postVehicleDTO.getModel());
        //vehicle.setUserId(postVehicleDTO.getUserId());
        vehicle.setPerson(person);
        vehicle.setDeleted(false);

        Vehicle saveVehicle = vehicleRepository.save(vehicle);
        return modelMapper.map(saveVehicle, GetVehicleDTO.class);
    }

    @Override
    public GetVehicleDTO update(Long id, PutVehicleDTO putVehicleDTO) {
        Vehicle existVehicle = vehicleRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Vehicle with id:" + id + " not found"));

        Person person = personRepository.findByIdAndIsDeletedFalse(putVehicleDTO.getPersonId())
                .orElseThrow(()->
                        new IdNotFoundException("Person with id:" + putVehicleDTO.getPersonId() + " not found"));

        existVehicle.setBrand(putVehicleDTO.getBrand());
        existVehicle.setModel(putVehicleDTO.getModel());
        existVehicle.setPerson(person);
        //existVehicle.setUserId(putVehicleDTO.getUserId());

       vehicleRepository.save(existVehicle);

        return modelMapper.map(existVehicle, GetVehicleDTO.class);
    }

    @Override
    public void delete(Long id) {
        Vehicle existVehicle = vehicleRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new IdNotFoundException("Vehicle with:" + id + " not found"));
        existVehicle.setDeleted(true);
        vehicleRepository.save(existVehicle);
    }
}
