package cz.cvut.fit.cola.colaserver.service;

import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.repository.AchievementRepository;
import cz.cvut.fit.cola.colaserver.exception.ResourceNotFoundException;
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

    public Achievement findById(Long id) {
        return achievementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Achievement not found with id: " + id));
    }

    public Achievement update(Long id, Achievement achievementDetails) {
        Achievement achievement = findById(id);
        
        achievement.setName(achievementDetails.getName());
        achievement.setDescription(achievementDetails.getDescription());
        achievement.setPoints(achievementDetails.getPoints());
        
        return achievementRepository.save(achievement);
    }

    public void delete(Long id) {
        Achievement achievement = findById(id);
        achievementRepository.delete(achievement);
    }
}
