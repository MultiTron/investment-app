package uni.pu.fmi.stocks.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import uni.pu.fmi.stocks.dtos.stock.CreateStockDto;
import uni.pu.fmi.stocks.dtos.stock.GetStockDto;
import uni.pu.fmi.stocks.dtos.stock.UpdateStockDto;
import uni.pu.fmi.models.Stock;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StockMapper {
    GetStockDto toDto(Stock source);
    List<GetStockDto> toDtoList(List<Stock> source);

    Stock toEntity(CreateStockDto source);
    Stock toEntity(UpdateStockDto source, @MappingTarget Stock target);
}
