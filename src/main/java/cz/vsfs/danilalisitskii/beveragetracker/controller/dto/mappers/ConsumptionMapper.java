package cz.vsfs.danilalisitskii.beveragetracker.controller.dto.mappers;

import cz.vsfs.danilalisitskii.beveragetracker.controller.dto.ConsumptionDto;
import cz.vsfs.danilalisitskii.beveragetracker.entity.Consumption;
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
