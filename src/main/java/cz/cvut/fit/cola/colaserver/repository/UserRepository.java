package cz.cvut.fit.cola.colaserver.repository;

import cz.cvut.fit.cola.colaserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    java.util.List<User> findAllByAchievementsContains(cz.cvut.fit.cola.colaserver.entity.Achievement achievement);
}
