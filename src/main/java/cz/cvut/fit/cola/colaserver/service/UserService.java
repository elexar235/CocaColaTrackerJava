package cz.cvut.fit.cola.colaserver.service;

import cz.cvut.fit.cola.colaserver.entity.User;
import cz.cvut.fit.cola.colaserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository; // final doesn't let anyone change the repo after service creation

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository; // IoC (Inversion Of Control)
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        return userRepository.save(user);
    }
}
