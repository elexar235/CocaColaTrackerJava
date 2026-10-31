package cz.vsfs.danilalisitskii.beveragetracker.repository;

import cz.vsfs.danilalisitskii.beveragetracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    java.util.List<User> findAllByAchievementsContains(cz.vsfs.danilalisitskii.beveragetracker.entity.Achievement achievement);
}
