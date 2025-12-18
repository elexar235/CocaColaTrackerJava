package cz.cvut.fit.cola.colaserver.repository;

import cz.cvut.fit.cola.colaserver.entity.Consumption;
import cz.cvut.fit.cola.colaserver.controller.dto.UserStatsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
    Long countByUserId(Long userId); // vmesto milliona vypisu tipa 100 ml, 500ml bla bla bla
                                     // prosto skazat skolko zapisej

    @Query("SELECT new cz.cvut.fit.cola.colaserver.controller.dto.UserStatsDto(u.username, SUM(c.amountMl)) " +
            "FROM Consumption c JOIN c.user u " +
            "GROUP BY u.username " +
            "ORDER BY SUM(c.amountMl) DESC")
    List<UserStatsDto> findTopDrinkers();
}
