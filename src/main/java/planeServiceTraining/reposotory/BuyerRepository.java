package planeServiceTraining.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import planeServiceTraining.domain.Buyer;


@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long> {
}
