package cz.cvut.fit.cola.colaserver.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AchievementCreateDto {
    @NotBlank(message = "Name is necessary.")
    private String name;

    @NotBlank(message = "Description is necessary.")
    private String description;

    @NotNull(message = "Points are necessary.")
    private Integer points;
    
    @NotNull(message = "Required consumptions are necessary.")
    private Integer requiredConsumptions;
}
