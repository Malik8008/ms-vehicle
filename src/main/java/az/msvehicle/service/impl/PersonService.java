package az.msvehicle.service.impl;

import az.msvehicle.dto.person.GetPersonDto;
import az.msvehicle.dto.person.PostPersonDto;
import az.msvehicle.dto.person.PutPersonDto;
import az.msvehicle.entity.Person;
import az.msvehicle.entity.Vehicle;
import az.msvehicle.exception.IdNotFoundException;
import az.msvehicle.repository.PersonRepository;
import az.msvehicle.service.IPersonService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    @Override
    public GetPersonDto getById(Long id) {
        Person existPerson = personRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new IdNotFoundException("Person with id: " +id+ " not found"));
        return modelMapper.map(existPerson,GetPersonDto.class);
    }

    @Override
    public List<GetPersonDto> getAll() {
        return personRepository.findAllByIsDeletedFalse()
                .stream().map(ps-> modelMapper.map(ps, GetPersonDto.class)).toList();
    }

    @Override
    public GetPersonDto create(PostPersonDto postPersonDto) {
        Person newPerson = new Person();
        newPerson.setAge(postPersonDto.getAge());
        newPerson.setName(postPersonDto.getName());
        newPerson.setSurname(postPersonDto.getSurname());
        newPerson.setPhone(postPersonDto.getPhone());
        newPerson.setDeleted(false);

        Person savePerson=personRepository.save(newPerson);
        return modelMapper.map(savePerson, GetPersonDto.class);
    }

    @Override
    public GetPersonDto update(Long id, PutPersonDto putPersonDto) {
        Person existPerson = personRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new IdNotFoundException("Person with id: " +id+ " not found"));

        existPerson.setAge(putPersonDto.getAge());
        existPerson.setName(putPersonDto.getName());
        existPerson.setSurname(putPersonDto.getSurname());
        existPerson.setPhone(putPersonDto.getPhone());

        Person updatePerson = personRepository.save(existPerson);

        return modelMapper.map(updatePerson, GetPersonDto.class);
    }

    @Override
    public void delete(Long id) {
        Person existPerson = personRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(()-> new IdNotFoundException("Person with id: " +id+ " not found"));
        existPerson.setDeleted(true);

        for (Vehicle vehicle : existPerson.getVehicles()) {
            vehicle.setDeleted(true);
        }
        personRepository.save(existPerson);
    }
}
