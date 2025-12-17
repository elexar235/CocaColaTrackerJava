package cz.cvut.fit.cola.colaserver.controller;

import cz.cvut.fit.cola.colaserver.service.ConsumptionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumptions")
public class ConsumptionController {
    private final ConsumptionService consumptionService;

    public ConsumptionController(ConsumptionService consumptionService) {
        this.consumptionService = consumptionService;
    }

    @PostMapping
    public String drinkCola(@RequestParam Long userId, @RequestParam Integer amountMl){
        consumptionService.addConsumption(userId, amountMl);
        return "Consumption has been added to the Cola";
    }
}
