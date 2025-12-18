package cz.cvut.fit.cola.colaserver.controller;

import cz.cvut.fit.cola.colaserver.controller.dto.AchievementCreateDto;
import cz.cvut.fit.cola.colaserver.controller.dto.AchievementDto;
import cz.cvut.fit.cola.colaserver.controller.dto.mappers.AchievementMapper;
import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.service.AchievementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievements")
public class AchievementController {

    private final AchievementService achievementService;
    private final AchievementMapper achievementMapper;

    public AchievementController(AchievementService achievementService, AchievementMapper achievementMapper){
        this.achievementService = achievementService;
        this.achievementMapper = achievementMapper;
    }

    @GetMapping
    public List<AchievementDto> findAll(){
        return achievementService.findAll().stream()
                .map(achievementMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public AchievementDto findById(@PathVariable Long id) {
        Achievement achievement = achievementService.findById(id);
        return achievementMapper.toDto(achievement);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AchievementDto create(@RequestBody @Valid AchievementCreateDto createDto){
        Achievement achievement = achievementMapper.toEntity(createDto);
        Achievement createdAchievement = achievementService.create(achievement);
        return achievementMapper.toDto(createdAchievement);
    }

    @PutMapping("/{id}")
    public AchievementDto update(@PathVariable Long id, @RequestBody @Valid AchievementCreateDto createDto) {
        Achievement achievementDetails = achievementMapper.toEntity(createDto);
        Achievement updatedAchievement = achievementService.update(id, achievementDetails);
        return achievementMapper.toDto(updatedAchievement);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        achievementService.delete(id);
    }
}
