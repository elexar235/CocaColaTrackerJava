package cz.cvut.fit.cola.colaserver.service;

import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.entity.User;
import cz.cvut.fit.cola.colaserver.repository.AchievementRepository;
import cz.cvut.fit.cola.colaserver.repository.UserRepository;
import cz.cvut.fit.cola.colaserver.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AchievementRepository achievementRepository;

    public UserService(UserRepository userRepository, AchievementRepository achievementRepository) {
        this.userRepository = userRepository;
        this.achievementRepository = achievementRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User userDetails) {
        User user = findById(id); 
        
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        
        return userRepository.save(user);
    }

    public void delete(Long id) {
        User user = findById(id); 
        userRepository.delete(user);
    }

    public User addAchievement(Long userId, Long achievementId) {
        User user = findById(userId);
        
        Achievement achievement = achievementRepository.findById(achievementId)
                .orElseThrow(() -> new ResourceNotFoundException("Achievement not found with id: " + achievementId));

        user.getAchievements().add(achievement);

        return userRepository.save(user);
    }
}
