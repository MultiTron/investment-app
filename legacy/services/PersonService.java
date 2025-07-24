package uni.pu.fmi.legacy.services;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonService<G, C, U> {
    List<G> getAll();

    G getById(Long id);
    void createPerson(C createPersonDto);
    void updatePerson(Long id, U updatePersonDto);
    void deletePerson(Long id);
}
