package uni.pu.fmi.persons.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uni.pu.fmi.persons.dtos.investors.CreateInvestorDto;
import uni.pu.fmi.persons.dtos.investors.GetInvestorDto;
import uni.pu.fmi.persons.dtos.investors.UpdateInvestorDto;
import uni.pu.fmi.models.Investor;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InvestorMapper {
    GetInvestorDto toDto(Investor source);
    List<GetInvestorDto> toDtoList(List<Investor> source);

    Investor toEntity(CreateInvestorDto source);
    Investor toEntity(UpdateInvestorDto source, @MappingTarget Investor target);
}

