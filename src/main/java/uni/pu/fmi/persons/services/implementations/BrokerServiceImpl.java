package uni.pu.fmi.persons.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.pu.fmi.exceptions.ResourceNotFoundException;
import uni.pu.fmi.models.Broker;
import uni.pu.fmi.persons.dtos.broker.CreateBrokerDto;
import uni.pu.fmi.persons.dtos.broker.GetBrokerDto;
import uni.pu.fmi.persons.dtos.broker.UpdateBrokerDto;
import uni.pu.fmi.persons.mappers.BrokerMapper;
import uni.pu.fmi.persons.repositories.BrokerRepository;
import uni.pu.fmi.persons.services.PersonService;

import java.util.List;
import java.util.UUID;

import static uni.pu.fmi.exceptions.ErrorConstants.brokerNotFound;

@Service
@Transactional
public class BrokerServiceImpl implements PersonService<GetBrokerDto, CreateBrokerDto, UpdateBrokerDto> {
    private final BrokerMapper mapper;
    private final BrokerRepository repo;

    public BrokerServiceImpl(BrokerRepository repo, BrokerMapper mapper) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @Override
    public List<GetBrokerDto> getAll() {
        return mapper.toDtoList(repo.findAll());
    }

    @Override
    public GetBrokerDto getById(UUID id) {
        return repo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(brokerNotFound(id)));
    }

    @Override
    public UUID createPerson(CreateBrokerDto createPersonDto) {
        return repo.save(mapper.toEntity(createPersonDto)).getId();
    }

    @Override
    public void updatePerson(UUID id, UpdateBrokerDto updatePersonDto) {
        Broker broker = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(brokerNotFound(id)));
        mapper.toEntity(updatePersonDto, broker);
    }

    @Override
    public void deletePerson(UUID id) {
        if (!repo.existsById(id)){
            throw new ResourceNotFoundException(brokerNotFound(id));
        }
        repo.deleteById(id);
    }
}
