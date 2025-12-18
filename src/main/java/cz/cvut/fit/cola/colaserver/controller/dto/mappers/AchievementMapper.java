package cz.cvut.fit.cola.colaserver.controller.dto.mappers;

import cz.cvut.fit.cola.colaserver.controller.dto.AchievementCreateDto;
import cz.cvut.fit.cola.colaserver.controller.dto.AchievementDto;
import cz.cvut.fit.cola.colaserver.entity.Achievement;
import org.springframework.stereotype.Component;

@Component
public class AchievementMapper {
    public AchievementDto toDto(Achievement achievement) {
        return new AchievementDto(
                achievement.getId(),
                achievement.getName(),
                achievement.getDescription(),
                achievement.getPoints(),
                achievement.getRequiredConsumptions()
        );
    }

    public Achievement toEntity(AchievementCreateDto dto) {
        Achievement achievement = new Achievement(
                dto.getName(),
                dto.getDescription(),
                dto.getPoints()
        );
        achievement.setRequiredConsumptions(dto.getRequiredConsumptions());
        return achievement;
    }
}