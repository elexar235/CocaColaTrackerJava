package cz.cvut.fit.cola.colaserver.repository;

import cz.cvut.fit.cola.colaserver.entity.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
    Long countByUserId(Long userId); // vmesto milliona vypisu tipa 100 ml, 500ml bla bla bla
}                                    // prosto skazat skolko zapisej
