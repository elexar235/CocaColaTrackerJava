package cz.cvut.fit.cola.colaserver.repository;

import cz.cvut.fit.cola.colaserver.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement,Long> {
    Achievement findByName(String achievementName);
}
