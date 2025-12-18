package cz.cvut.fit.cola.colaserver.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AchievementCreateDto {
    private String name;
    private String description;
    private Integer points;
}
