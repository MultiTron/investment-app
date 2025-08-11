package uni.pu.fmi.persons.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uni.pu.fmi.persons.dtos.broker.CreateBrokerDto;
import uni.pu.fmi.persons.dtos.broker.GetBrokerDto;
import uni.pu.fmi.persons.dtos.broker.UpdateBrokerDto;
import uni.pu.fmi.models.Broker;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BrokerMapper {
    GetBrokerDto toDto(Broker source);
    List<GetBrokerDto> toDtoList(List<Broker> source);

    Broker toEntity(CreateBrokerDto source);
    Broker toEntity(UpdateBrokerDto source, @MappingTarget Broker target);
}
