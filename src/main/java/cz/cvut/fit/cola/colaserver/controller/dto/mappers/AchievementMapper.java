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
                achievement.getPoints()
        );
    }

    public Achievement toEntity(AchievementCreateDto dto) {
        return new Achievement(
                dto.getName(),
                dto.getDescription(),
                dto.getPoints()
        );
    }
}