package cz.cvut.fit.cola.colaserver.controller.dto.mappers;

import cz.cvut.fit.cola.colaserver.controller.dto.ConsumptionDto;
import cz.cvut.fit.cola.colaserver.entity.Consumption;
import org.springframework.stereotype.Component;

@Component
public class ConsumptionMapper {

    public ConsumptionDto toDto(Consumption consumption) {
        if (consumption == null) return null;
        
        return new ConsumptionDto(
                consumption.getId(),
                consumption.getUser().getId(),
                consumption.getUser().getUsername(),
                consumption.getAmountMl(),
                consumption.getCreatedAt()
        );
    }
}
