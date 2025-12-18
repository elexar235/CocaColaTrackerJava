package cz.cvut.fit.cola.colaserver.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsumptionDto {
    private Long id;
    private Long userId;
    private String username;
    private Integer amountMl;
    private LocalDateTime createdAt;
}
