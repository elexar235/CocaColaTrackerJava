package cz.cvut.fit.cola.colaserver.controller.dto.mappers;

import cz.cvut.fit.cola.colaserver.controller.dto.UserDto;
import cz.cvut.fit.cola.colaserver.entity.Achievement;
import cz.cvut.fit.cola.colaserver.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) { return null; }

        var achievementNames = user.getAchievements().stream()
                .map(Achievement::getName)
                .toList();
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                achievementNames
        );
    }

    public User toEntity(UserCreateDto userCreateDto) {
        return new User(
                userCreateDto.getUsername(),
                userCreateDto.getEmail()
        );
    }
}
