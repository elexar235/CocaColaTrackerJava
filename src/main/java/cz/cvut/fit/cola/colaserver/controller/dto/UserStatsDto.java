// oddelnij DTO dlja JPQL, potomu chto nuzny chisto name and totalAmountMl
package cz.cvut.fit.cola.colaserver.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserStatsDto {
    private String username;
    private Long totalAmountMl;
}
