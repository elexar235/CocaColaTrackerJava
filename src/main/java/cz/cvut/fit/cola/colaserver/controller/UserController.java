package cz.cvut.fit.cola.colaserver.controller;

import cz.cvut.fit.cola.colaserver.entity.User;
import cz.cvut.fit.cola.colaserver.repository.UserRepository;
import cz.cvut.fit.cola.colaserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll()
    {
        return userService.findAll();
    }

    @PostMapping
    public User create(@RequestBody @Valid User user)
    {
        return userService.create(user);
    }

    @PostMapping("/{userId}/achievements/{achievementId}")
    public User addAchievementToUser(@PathVariable Long userId, @PathVariable Long achievementId)
    {
        return userService.addAchievement(userId, achievementId);
    }
}
