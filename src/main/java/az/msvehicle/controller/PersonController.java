package az.msvehicle.controller;

import az.msvehicle.dto.person.GetPersonDto;
import az.msvehicle.dto.person.PostPersonDto;
import az.msvehicle.dto.person.PutPersonDto;
import az.msvehicle.service.impl.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping()
    public ResponseEntity<List<GetPersonDto>> getAll(){
        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPersonDto> getPersonById(@PathVariable Long id){
        return ResponseEntity.ok(personService.getById(id));
    }

    @PostMapping()
    public ResponseEntity<GetPersonDto> create(@RequestBody PostPersonDto dto){
        return ResponseEntity.ok(personService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetPersonDto> update(@PathVariable Long id,@RequestBody PutPersonDto dto){
        return ResponseEntity.ok(personService.update(id,dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
