package cz.cvut.fit.cola.colaserver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "achievements")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is necessary.")
    private String name;

    private String description;

    private Integer points;

    public Achievement (String name, String description, Integer points) {
        this.name = name;
        this.description = description;
        this.points = points;
    }


}
