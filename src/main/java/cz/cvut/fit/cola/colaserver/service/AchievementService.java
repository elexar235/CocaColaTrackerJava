package cz.cvut.fit.cola.colaserver.service;

import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.entity.User;
import cz.cvut.fit.cola.colaserver.repository.AchievementRepository;
import cz.cvut.fit.cola.colaserver.exception.ResourceNotFoundException;
import cz.cvut.fit.cola.colaserver.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AchievementService {
    private final AchievementRepository achievementRepository;
    private final UserRepository userRepository;

    public AchievementService(AchievementRepository achievementRepository, UserRepository userRepository) {
        this.achievementRepository = achievementRepository;
        this.userRepository = userRepository;
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

    @Transactional
    public void delete(Long id) {
        Achievement achievement = findById(id);
        
        // Remove this achievement from all users who have it
        List<User> users = userRepository.findAllByAchievementsContains(achievement);
        for (User user : users) {
             user.getAchievements().remove(achievement);
             userRepository.save(user);
        }
        
        achievementRepository.delete(achievement);
    }
}
