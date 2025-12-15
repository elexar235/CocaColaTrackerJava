package cz.cvut.fit.cola.colaserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @Column (nullable = false, unique = true) // uniq name
    private String username;

    @Column(nullable = false)
    private String email;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
