package cz.cvut.fit.cola.colaserver.service;

import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.entity.User;
import cz.cvut.fit.cola.colaserver.exception.ResourceNotFoundException;
import cz.cvut.fit.cola.colaserver.repository.AchievementRepository;
import cz.cvut.fit.cola.colaserver.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AchievementRepository achievementRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void create_ShouldSaveUser() {
        User user = new User();
        user.setUsername("testuser");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User created = userService.create(user);

        assertNotNull(created);
        assertEquals("testuser", created.getUsername());
        verify(userRepository).save(user);
    }

    @Test
    void findById_WhenExists_ShouldReturnUser() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User found = userService.findById(1L);

        assertNotNull(found);
        assertEquals(1L, found.getId());
    }

    @Test
    void findById_WhenNotExists_ShouldThrowException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById(99L));
    }

    @Test
    void addAchievement_ShouldAddAchievementToUser() {
        User user = new User();
        user.setId(1L);
        
        Achievement achievement = new Achievement();
        achievement.setId(10L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(achievementRepository.findById(10L)).thenReturn(Optional.of(achievement));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0)); // returns what was saved

        User updatedUser = userService.addAchievement(1L, 10L);

        assertTrue(updatedUser.getAchievements().contains(achievement));
        verify(userRepository).save(user);
    }
}
