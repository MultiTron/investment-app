package uni.pu.fmi.persons.services;

import java.util.List;

public interface PersonService<G, C, U> {
    List<G> getAll();

    G getById(Long id);
    Long createPerson(C createPersonDto);
    void updatePerson(Long id, U updatePersonDto);
    void deletePerson(Long id);
}
