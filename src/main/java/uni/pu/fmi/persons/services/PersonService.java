package uni.pu.fmi.persons.services;

import java.util.List;
import java.util.UUID;

public interface PersonService<G, C, U> {
    List<G> getAll();

    G getById(UUID id);
    UUID createPerson(C createPersonDto);
    void updatePerson(UUID id, U updatePersonDto);
    void deletePerson(UUID id);
}
