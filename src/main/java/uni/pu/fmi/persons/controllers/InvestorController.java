package uni.pu.fmi.persons.controllers;

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
import uni.pu.fmi.persons.dtos.investors.CreateInvestorDto;
import uni.pu.fmi.persons.dtos.investors.GetInvestorDto;
import uni.pu.fmi.persons.dtos.investors.UpdateInvestorDto;
import uni.pu.fmi.persons.services.implementations.InvestorServiceImpl;
import uni.pu.fmi.stocks.dtos.stock.GetStockDto;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/investors")
public class InvestorController {
    private final InvestorServiceImpl service;

    public InvestorController(InvestorServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetInvestorDto>> getInvestors() {
        return ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetInvestorDto> getInvestorById(@PathVariable UUID id) {
        return ok(service.getById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createInvestor(@RequestBody @Valid CreateInvestorDto dto) {
        UUID id = service.createPerson(dto);
        return created(URI.create("/investors/" + id)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateInvestor(@PathVariable UUID id, @RequestBody UpdateInvestorDto dto) {
        service.updatePerson(id, dto);
        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvestor(@PathVariable UUID id) {
        service.deletePerson(id);
        return ok().build();
    }

    @GetMapping("/{id}/watchlist")
    public ResponseEntity<List<GetStockDto>> getStocksInWatchlist(@PathVariable UUID id){
        return ok(service.getWatchlist(id));
    }

    @PostMapping("/{investorId}/watchlist/add/{stockId}")
    public ResponseEntity<?> addToWatchlist(@PathVariable UUID investorId, @PathVariable UUID stockId){
        service.addToWatchlist(investorId, stockId);
        return ok().build();
    }

    @DeleteMapping("/{investorId}/watchlist/remove/{stockId}")
    public ResponseEntity<?> removeFromWatchlist(@PathVariable UUID investorId, @PathVariable UUID stockId){
        service.removeFromWatchlist(investorId, stockId);
        return ok().build();
    }
}
