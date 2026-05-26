package az.msvehicle.controller;

import az.msvehicle.dto.vehicle.GetVehicleDTO;
import az.msvehicle.dto.vehicle.PostVehicleDTO;
import az.msvehicle.dto.vehicle.PutVehicleDTO;
import az.msvehicle.service.impl.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<GetVehicleDTO>> getAll() {
        return ResponseEntity.ok(vehicleService.getVehicles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetVehicleDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<GetVehicleDTO>> getByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.findByUserId(id));
    }

    @PostMapping
    public ResponseEntity<GetVehicleDTO> create(@RequestBody PostVehicleDTO postVehicleDTO) {
        return ResponseEntity.ok(vehicleService.create(postVehicleDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetVehicleDTO> update(@PathVariable Long id,
                                                @RequestBody PutVehicleDTO putVehicleDTO) {
        return ResponseEntity.ok(vehicleService.update(id, putVehicleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
