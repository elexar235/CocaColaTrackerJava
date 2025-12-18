package cz.cvut.fit.cola.colaserver.controller;

import cz.cvut.fit.cola.colaserver.controller.dto.ConsumptionCreateDto;
import cz.cvut.fit.cola.colaserver.controller.dto.ConsumptionDto;
import cz.cvut.fit.cola.colaserver.controller.dto.mappers.ConsumptionMapper;
import cz.cvut.fit.cola.colaserver.entity.Consumption;
import cz.cvut.fit.cola.colaserver.service.ConsumptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumptions")
public class ConsumptionController {
    
    private final ConsumptionService consumptionService;
    private final ConsumptionMapper consumptionMapper;

    public ConsumptionController(ConsumptionService consumptionService, ConsumptionMapper consumptionMapper) {
        this.consumptionService = consumptionService;
        this.consumptionMapper = consumptionMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumptionDto drinkCola(@RequestBody @Valid ConsumptionCreateDto createDto) {
        if (createDto.getAmountMl() < 330) {
            throw new IllegalArgumentException("Amount must be at least 330, lol, a sip doesn't count");
        }
        
        Consumption consumption = consumptionService.addConsumption(createDto.getUserId(), createDto.getAmountMl());
        return consumptionMapper.toDto(consumption);
    }

    @GetMapping
    public java.util.List<ConsumptionDto> getAllConsumptions() {
        return consumptionService.findAll().stream()
                .map(consumptionMapper::toDto)
                .toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsumption(@PathVariable Long id) {
        consumptionService.deleteConsumption(id);
    }

    @GetMapping("/stats/leaderboard")
    public java.util.List<cz.cvut.fit.cola.colaserver.controller.dto.UserStatsDto> getLeaderboard() {
        return consumptionService.getTopDrinkers();
    }
}
