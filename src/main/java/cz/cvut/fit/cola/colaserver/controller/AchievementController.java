package cz.cvut.fit.cola.colaserver.controller;

import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.service.AchievementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievements")
public class AchievementController {

    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService){
        this.achievementService = achievementService;
    }

    @GetMapping
    public List<Achievement> getAchievements(){
        return achievementService.findAll();
    }

    @PostMapping
    public Achievement create(@RequestBody Achievement achievement){
        return achievementService.create(achievement);
    }
}
