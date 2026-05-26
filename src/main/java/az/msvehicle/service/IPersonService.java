package az.msvehicle.service;

import az.msvehicle.dto.person.GetPersonDto;
import az.msvehicle.dto.person.PostPersonDto;
import az.msvehicle.dto.person.PutPersonDto;

import java.util.List;

public interface IPersonService {
    GetPersonDto getById(Long id);
    List<GetPersonDto> getAll();
    GetPersonDto create(PostPersonDto postPersonDto);
    GetPersonDto update(Long id, PutPersonDto putPersonDto);
    void delete(Long id);
}
