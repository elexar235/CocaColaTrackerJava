package cz.cvut.fit.cola.colaserver.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @ManyToMany
    @JoinTable(
            name = "user_achievements",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id")
    )

    private List<Achievement> achievements = new ArrayList<>(); //zdec hibernate peredelyvaet vazbu M:N na pravilnuju (odsuda pojavlajetsa jeste jedna table)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true) // uniq name
    @NotBlank(message = "Name cannot be blank.") // checks whether username is blank
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Email is necessary.")
    @Email(message = "Doesn't look like an email.") // checks whether it has @ and .
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
