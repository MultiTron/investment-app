package uni.pu.fmi.stocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uni.pu.fmi.exceptions.ErrorConstants;
import uni.pu.fmi.exceptions.ResourceNotFoundException;
import uni.pu.fmi.models.Stock;
import uni.pu.fmi.stocks.dtos.stock.CreateStockDto;
import uni.pu.fmi.stocks.mappers.StockMapper;
import uni.pu.fmi.stocks.repositories.StockRepository;
import uni.pu.fmi.stocks.services.implementations.StockServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uni.pu.fmi.exceptions.ErrorConstants.*;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {
    final StockMapper mapper = Mappers.getMapper(StockMapper.class);

    @Mock
    StockRepository repo;

    @InjectMocks
    StockServiceImpl service;

    @BeforeEach
    void setUp(){
        this.service = new StockServiceImpl(mapper, repo);
    }

    @Test
    void successGetStockById(){
        when(repo.findById(1L))
                .thenReturn(Optional.of(new Stock("Nasdaq", "NSD")));

        var stockDto = service.getById(1L);

        assertEquals("Nasdaq", stockDto.name());
    }

    @Test
    void notFoundGetStockById(){
        when(repo.findById(1L))
                .thenThrow(new ResourceNotFoundException(stockNotFound(1L)));

        assertThrows(ResourceNotFoundException.class, () -> {
            service.getById(1L);
        });
    }

    @Test
    void successDeleteStock(){
        when(repo.existsById(1L)).thenReturn(true);

        service.deleteStock(1L);

        verify(repo).deleteById(1L);
    }

    @Test
    void successCreateStock(){
        //TODO Stock creation test
    }
}
