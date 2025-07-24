package uni.pu.fmi.legacy.controllers;

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
import uni.pu.fmi.legacy.dtos.investor.CreateInvestorDto;
import uni.pu.fmi.legacy.dtos.investor.GetInvestorDto;
import uni.pu.fmi.legacy.dtos.investor.UpdateInvestorDto;
import uni.pu.fmi.legacy.services.implementations.InvestorServiceImpl;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/investors")
public class InvestorController {
    private final InvestorServiceImpl service;

    public InvestorController(InvestorServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetInvestorDto>> getInvestors()
    {
        return ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetInvestorDto> getInvestorById(@PathVariable Long id)
    {
        if (service.getById(id) == null){
            return notFound().build();
        }
        return ok(service.getById(id));
    }
    @PostMapping("/new")
    public ResponseEntity createInvestor(@RequestBody @Valid CreateInvestorDto dto){
        service.createPerson(dto);
        return ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateInvestor(@PathVariable Long id, @RequestBody UpdateInvestorDto dto){
        service.updatePerson(id, dto);
        return ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInvestor(@PathVariable Long id){
        service.deletePerson(id);
        return ok().build();
    }
}
