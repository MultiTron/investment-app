package uni.pu.fmi.legacy.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uni.pu.fmi.legacy.dtos.broker.CreateBrokerDto;
import uni.pu.fmi.legacy.dtos.broker.GetBrokerDto;
import uni.pu.fmi.legacy.dtos.broker.UpdateBrokerDto;
import uni.pu.fmi.legacy.models.Broker;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BrokerMapper {
    GetBrokerDto toDto(Broker broker);
    List<GetBrokerDto> toDtoList(List<Broker> brokers);

    Broker toEntity(CreateBrokerDto dto);
    Broker toEntity(UpdateBrokerDto dto, @MappingTarget Broker broker);
}
