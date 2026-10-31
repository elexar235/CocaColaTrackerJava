package cz.vsfs.danilalisitskii.beveragetracker.controller.dto.mappers;

import cz.vsfs.danilalisitskii.beveragetracker.controller.dto.AchievementCreateDto;
import cz.vsfs.danilalisitskii.beveragetracker.controller.dto.AchievementDto;
import cz.vsfs.danilalisitskii.beveragetracker.entity.Achievement;
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