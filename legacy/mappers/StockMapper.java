package uni.pu.fmi.legacy.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uni.pu.fmi.legacy.dtos.stock.CreateStockDto;
import uni.pu.fmi.legacy.dtos.stock.GetStockDto;
import uni.pu.fmi.legacy.dtos.stock.UpdateStockDto;
import uni.pu.fmi.legacy.models.Stock;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StockMapper {
    GetStockDto toDto(Stock stock);
    List<GetStockDto> toDtoList(List<Stock> stock);

    Stock toEntity(CreateStockDto dto);
    Stock toEntity(UpdateStockDto dto, @MappingTarget Stock stock);
}
