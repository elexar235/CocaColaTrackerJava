package cz.cvut.fit.cola.colaserver.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true) // uniq name
    private String username;

    @Column(nullable = false)
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) // same references
            return true;
        if (obj == null || getClass() != obj.getClass()) // null or different class
            return false;

        User user = (User) obj;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode(); // not Hash of ID, because it's going to calculate Hash of null (before db gives a correct ID to User)
    }

}
