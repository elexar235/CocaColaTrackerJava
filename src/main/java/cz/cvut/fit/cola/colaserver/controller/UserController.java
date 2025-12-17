package cz.cvut.fit.cola.colaserver.controller;

import cz.cvut.fit.cola.colaserver.controller.dto.UserCreateDto;
import cz.cvut.fit.cola.colaserver.controller.dto.UserDto;
import cz.cvut.fit.cola.colaserver.controller.dto.mappers.UserMapper;
import cz.cvut.fit.cola.colaserver.entity.User;
import cz.cvut.fit.cola.colaserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return userMapper.toDto(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid UserCreateDto userCreateDto) {
        User user = userMapper.toEntity(userCreateDto);
        User createdUser = userService.create(user);
        return userMapper.toDto(createdUser);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody @Valid UserCreateDto userCreateDto) {
        User userDetails = userMapper.toEntity(userCreateDto);
        
        User updatedUser = userService.update(id, userDetails);
        return userMapper.toDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PostMapping("/{userId}/achievements/{achievementId}")
    public UserDto addAchievementToUser(@PathVariable Long userId, @PathVariable Long achievementId) {
        User updatedUser = userService.addAchievement(userId, achievementId);
        return userMapper.toDto(updatedUser);
    }
}
