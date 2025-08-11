package uni.pu.fmi.stocks.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.pu.fmi.stocks.dtos.stock.CreateStockDto;
import uni.pu.fmi.stocks.dtos.stock.GetStockDto;
import uni.pu.fmi.stocks.dtos.stock.UpdateStockDto;
import uni.pu.fmi.stocks.services.implementations.StockServiceImpl;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private final StockServiceImpl service;

    public StockController(StockServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetStockDto>> getStock() {
        return ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetStockDto> getStockById(@PathVariable UUID id) {
        return ok(service.getById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createStock(@RequestBody @Valid CreateStockDto dto) {
        UUID id = service.createStock(dto);
        return created(URI.create("/stocks/" + id)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateStock(@PathVariable UUID id, @RequestBody UpdateStockDto dto) {
        service.updateStock(id, dto);
        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable UUID id) {
        service.deleteStock(id);
        return ok().build();
    }
}
