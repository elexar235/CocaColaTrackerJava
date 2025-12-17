package cz.cvut.fit.cola.colaserver.service;

import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.entity.User;
import cz.cvut.fit.cola.colaserver.repository.AchievementRepository;
import cz.cvut.fit.cola.colaserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository; // final doesn't let anyone change the repo after service creation
    private final AchievementRepository achievementRepository;

    public UserService(UserRepository userRepository,  AchievementRepository achievementRepository) {
        this.userRepository = userRepository; // IoC (Inversion Of Control)
        this.achievementRepository = achievementRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User addAchievement(Long userId, Long achievementId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Achievement achievement = achievementRepository.findById(achievementId)
                .orElseThrow(() -> new RuntimeException("Achievement not found"));

        user.getAchievements().add(achievement);

        return userRepository.save(user);
    }
}
