package cz.cvut.fit.cola.colaserver.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;

    private List<String> achievements; // just strings instead of Achievement objects to avoid stackoverflow

    public UserDto(Long id, String username, String email, List<String> achievements) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.achievements = achievements;
    }
}
