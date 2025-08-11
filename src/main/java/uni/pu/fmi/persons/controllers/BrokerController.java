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
import uni.pu.fmi.persons.dtos.broker.CreateBrokerDto;
import uni.pu.fmi.persons.dtos.broker.GetBrokerDto;
import uni.pu.fmi.persons.dtos.broker.UpdateBrokerDto;
import uni.pu.fmi.persons.services.implementations.BrokerServiceImpl;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/brokers")
public class BrokerController {
    private final BrokerServiceImpl service;

    public BrokerController(BrokerServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetBrokerDto>> getBrokers() {
        return ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetBrokerDto> getBrokerById(@PathVariable UUID id) {
        return ok(service.getById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createBroker(@RequestBody @Valid CreateBrokerDto dto) {
        UUID id = service.createPerson(dto);
        return created(URI.create("/brokers/" + id)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBroker(@PathVariable UUID id, @RequestBody UpdateBrokerDto dto) {
        service.updatePerson(id, dto);
        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBroker(@PathVariable UUID id) {
        service.deletePerson(id);
        return ok().build();
    }
}
