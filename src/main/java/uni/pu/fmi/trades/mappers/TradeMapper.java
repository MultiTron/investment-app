package uni.pu.fmi.trades.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import uni.pu.fmi.models.Trade;
import uni.pu.fmi.persons.mappers.BrokerMapper;
import uni.pu.fmi.persons.mappers.InvestorMapper;
import uni.pu.fmi.stocks.mappers.StockMapper;
import uni.pu.fmi.trades.dtos.trade.CreateTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetFullTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetTradeDto;
import uni.pu.fmi.trades.dtos.trade.UpdateTradeDto;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {InvestorMapper.class, StockMapper.class, BrokerMapper.class})
public interface TradeMapper {
    @Mapping(source = "investor.id", target = "investorId")
    @Mapping(source = "stock.id", target = "stockId")
    @Mapping(source = "broker.id", target = "brokerId")
    GetTradeDto toDto(Trade source);

    List<GetTradeDto> toDtoList(List<Trade> source);

    @Mapping(source = "investor", target = "investor")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "broker", target = "broker")
    GetFullTradeDto toFullDto(Trade source);

    List<GetFullTradeDto> toFullDtoList(List<Trade> source);

    @Mapping(source = "investorId", target = "investor.id")
    @Mapping(source = "stockId", target = "stock.id")
    @Mapping(source = "brokerId", target = "broker.id")
    Trade toEntity(CreateTradeDto source);

    Trade toEntity(UpdateTradeDto source, @MappingTarget Trade target);
}
