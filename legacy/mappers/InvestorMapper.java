package uni.pu.fmi.legacy.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uni.pu.fmi.legacy.dtos.investor.CreateInvestorDto;
import uni.pu.fmi.legacy.dtos.investor.GetInvestorDto;
import uni.pu.fmi.legacy.dtos.investor.UpdateInvestorDto;
import uni.pu.fmi.legacy.models.Investor;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InvestorMapper {
    GetInvestorDto toDto(Investor investor);
    List<GetInvestorDto> toDtoList(List<Investor> investor);

    Investor toEntity(CreateInvestorDto dto);
    Investor toEntity(UpdateInvestorDto dto, @MappingTarget Investor investor);
}

