package az.msvehicle.client;

import az.msvehicle.dto.UserDTO.GetUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-user", url = "http://localhost:8081")
public interface UserClient {
    @GetMapping("/user/{id}")
    GetUserDto getUserById(@PathVariable Long id);
}
