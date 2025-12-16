package cz.cvut.fit.cola.colaserver.service;

import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.repository.AchievementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {
    private final AchievementRepository achievementRepository;

    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    public List<Achievement> findAll(){
        return achievementRepository.findAll();
    }

    public Achievement create(Achievement achievement){
        return achievementRepository.save(achievement);
    }
}
