package uni.pu.fmi.trades.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uni.pu.fmi.trades.dtos.trade.CreateTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetFullTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetTradeDto;
import uni.pu.fmi.trades.dtos.trade.UpdateTradeDto;
import uni.pu.fmi.trades.services.TradeService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/trades")
public class TradeController {
    private final TradeService service;

    public TradeController(TradeService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetTradeDto>> getTrades() {
        return ok(service.getAll());
    }

    @GetMapping("/all/full")
    public ResponseEntity<List<GetFullTradeDto>> getTradesFull() {
        return ok(service.getAllFull());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTradeDto> getTrade(@PathVariable Long id) {
        return ok(service.getById(id));
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<GetFullTradeDto> getTradeFull(@PathVariable Long id) {
        return ok(service.getFullById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createTrade(@RequestBody CreateTradeDto dto) {
        Long id = service.createTrade(dto);
        return created(URI.create("/trades/" + id)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTrade(@PathVariable Long id, @RequestBody UpdateTradeDto dto) {
        service.updateTrade(id, dto);
        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrade(@PathVariable Long id) {
        service.deleteTrade(id);
        return ok().build();
    }
}
