package cz.cvut.fit.cola.colaserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "consumptions")
public class Consumption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Integer amountMl;

    private LocalDateTime createdAt;

    public Consumption(User user, Integer amountMl, LocalDateTime createdAt) {
        this.user = user;
        this.amountMl = amountMl;
        this.createdAt = LocalDateTime.now();
    }
}
