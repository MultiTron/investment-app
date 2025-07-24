package uni.pu.fmi.legacy.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.pu.fmi.legacy.dtos.broker.CreateBrokerDto;
import uni.pu.fmi.legacy.dtos.broker.GetBrokerDto;
import uni.pu.fmi.legacy.dtos.broker.UpdateBrokerDto;
import uni.pu.fmi.legacy.mappers.BrokerMapper;
import uni.pu.fmi.legacy.models.Broker;
import uni.pu.fmi.legacy.repositories.BrokerRepository;
import uni.pu.fmi.legacy.services.PersonService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrokerServiceImpl implements PersonService<GetBrokerDto, CreateBrokerDto, UpdateBrokerDto> {
    private BrokerMapper mapper;
    private BrokerRepository repo;

    public BrokerServiceImpl(BrokerRepository repo, BrokerMapper mapper) {
        this.mapper = mapper;
        this.repo = repo;
    }

    @Override
    public List<GetBrokerDto> getAll() {
        return mapper.toDtoList(repo.findAll());
    }

    @Override
    public GetBrokerDto getById(Long id) {
        Optional<GetBrokerDto> optional = repo.findById(id).map(mapper::toDto);
        return optional.orElse(null);
    }

    @Override
    public void createPerson(CreateBrokerDto createPersonDto) {
        repo.save(mapper.toEntity(createPersonDto));
    }

    @Override
    public void updatePerson(Long id, UpdateBrokerDto updatePersonDto) {
        Broker broker = repo.findById(id).orElse(null);
        mapper.toEntity(updatePersonDto, broker);
    }

    @Override
    public void deletePerson(Long id) {
        repo.deleteById(id);
    }
}
