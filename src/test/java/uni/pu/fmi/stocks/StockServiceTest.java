package uni.pu.fmi.stocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uni.pu.fmi.exceptions.ResourceNotFoundException;
import uni.pu.fmi.models.Stock;
import uni.pu.fmi.stocks.dtos.stock.CreateStockDto;
import uni.pu.fmi.stocks.dtos.stock.GetStockDto;
import uni.pu.fmi.stocks.dtos.stock.UpdateStockDto;
import uni.pu.fmi.stocks.mappers.StockMapper;
import uni.pu.fmi.stocks.repositories.StockRepository;
import uni.pu.fmi.stocks.services.implementations.StockServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {
    @Mock
    StockMapper mapper;

    @Mock
    StockRepository repo;

    @InjectMocks
    StockServiceImpl service;

    @Test
    void successGetAllStocks() {
        List<Stock> stockList = List.of(new Stock("Test1", "TS1"), new Stock("Test2", "TS2"), new Stock("Test3", "TS3"));

        for (long i = 0; i < 3; i++) {
            stockList.get((int) i).setId(i + 1);
        }

        List<GetStockDto> dtoList = List.of(new GetStockDto("Test1", "TS1"), new GetStockDto("Test2", "TS2"), new GetStockDto("Test3", "TS3"));

        when(repo.findAll()).thenReturn(stockList);

        when(mapper.toDtoList(anyList())).thenReturn(dtoList);

        var list = service.getAll();

        verify(repo).findAll();

        assertArrayEquals(
                list.toArray(),
                dtoList.toArray());
    }

    @Test
    void successGetStockById() {
        var stock = new Stock("Nasdaq", "NSD");
        stock.setId(1L);

        when(mapper.toDto(stock)).thenReturn(new GetStockDto("Nasdaq", "NSD"));

        doReturn(Optional.of(stock)).when(repo).findById(1L);

        var stockDto = service.getById(1L);

        assertEquals("Nasdaq", stockDto.name());
    }

    @Test
    void notFoundGetStockById() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void successDeleteStock() {
        when(repo.existsById(1L)).thenReturn(true);

        service.deleteStock(1L);

        verify(repo).deleteById(1L);
    }

    @Test
    void notFoundDeleteStock() {
        when(repo.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.deleteStock(1L));
    }

    @Test
    void successCreateStock() {
        var stockDto = new CreateStockDto("test", "TST");
        var stock = new Stock("test", "TST");
        stock.setId(2L);

        when(mapper.toEntity(stockDto)).thenReturn(stock);

        when(repo.save(stock)).thenReturn(stock);

        UUID id = service.createStock(stockDto);

        verify(repo).save(mapper.toEntity(stockDto));

        assertEquals(2L, id);
    }

    @Test
    void successUpdateStock() {
        var stock = new Stock("OldName", "OLD");
        stock.setId(1L);

        var dto = new UpdateStockDto("NewName", "NEW");

        when(repo.findById(1L)).thenReturn(Optional.of(stock));

        doAnswer(invocation -> {
            UpdateStockDto source = invocation.getArgument(0);
            Stock target = invocation.getArgument(1);

            target.setName(source.name());
            target.setSymbol(source.symbol());
            return null;
        }).when(mapper).toEntity(any(UpdateStockDto.class), any(Stock.class));

        service.updateStock(1L, dto);

        assertEquals("NewName", stock.getName());
        assertEquals("NEW", stock.getSymbol());
    }

    @Test
    void notFoundUpdateStock() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrowsExactly(ResourceNotFoundException.class, () -> service.updateStock(1L, new UpdateStockDto("Test", "TST")));
    }
}
