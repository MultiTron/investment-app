package uni.pu.fmi.legacy.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uni.pu.fmi.legacy.dtos.stock.CreateStockDto;
import uni.pu.fmi.legacy.dtos.stock.GetStockDto;
import uni.pu.fmi.legacy.dtos.stock.UpdateStockDto;
import uni.pu.fmi.legacy.services.implementations.StockServiceImpl;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class StockController {
    private final StockServiceImpl service;

    public StockController(StockServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetStockDto>> getStock()
    {
        return ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetStockDto> getStockById(@PathVariable Long id)
    {
        if (service.getById(id) == null){
            return notFound().build();
        }
        return ok(service.getById(id));
    }
    @PostMapping("/new")
    public ResponseEntity createStock(@RequestBody @Valid CreateStockDto dto){
        service.createStock(dto);
        return ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateStock(@PathVariable Long id, @RequestBody UpdateStockDto dto){
        service.updateStock(id, dto);
        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStock(@PathVariable Long id){
        service.deleteStock(id);
        return ok().build();
    }
}
