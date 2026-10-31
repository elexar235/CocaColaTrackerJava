package cz.vsfs.danilalisitskii.beveragetracker.repository;

import cz.vsfs.danilalisitskii.beveragetracker.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement,Long> {
    Achievement findByName(String achievementName);
}
