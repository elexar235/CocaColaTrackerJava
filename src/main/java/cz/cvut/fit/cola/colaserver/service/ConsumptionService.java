package cz.cvut.fit.cola.colaserver.service;

import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.entity.Consumption;
import cz.cvut.fit.cola.colaserver.entity.User;
import cz.cvut.fit.cola.colaserver.repository.AchievementRepository;
import cz.cvut.fit.cola.colaserver.repository.ConsumptionRepository;
import cz.cvut.fit.cola.colaserver.repository.UserRepository;
import cz.cvut.fit.cola.colaserver.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsumptionService {
    private final ConsumptionRepository consumptionRepository;
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;

    public ConsumptionService(ConsumptionRepository consumptionRepository, UserRepository userRepository, AchievementRepository achievementRepository) {
        this.consumptionRepository = consumptionRepository;
        this.userRepository = userRepository;
        this.achievementRepository = achievementRepository;
    }

    @Transactional
    public Consumption addConsumption(Long userId, Integer amountMl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Consumption consumption = new Consumption();
        consumption.setUser(user);
        consumption.setAmountMl(amountMl);
        consumption.setCreatedAt(LocalDateTime.now());
        consumption = consumptionRepository.save(consumption);

        checkAchievements(user);
        
        return consumption;
    }

    public void checkAchievements(User user) {
        long totalDrinks = consumptionRepository.countByUserId(user.getId());

        if (totalDrinks == 1) {
            assignAchievement(user, "Novice Drinker");
        }
        if (totalDrinks == 5) {
            assignAchievement(user, "Sugar Rush");
        }
        if (totalDrinks == 10) {
            assignAchievement(user, "Diabetes loading...lol");
        }
    }

    public void assignAchievement(User user, String achievementName) {
        Achievement achievement = achievementRepository.findByName(achievementName);

        if (achievement != null) {
            if (!user.getAchievements().contains(achievement)) {
                user.getAchievements().add(achievement);
                userRepository.save(user);
                System.out.println("Achievement " + achievementName + " has been assigned to user " + user.getUsername());
            } else {
                System.out.println("Error: achievement " + achievementName + " is already assigned to user " + user.getUsername());
            }
        }
    }
}
