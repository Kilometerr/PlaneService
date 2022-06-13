package planeServiceTraining.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import planeServiceTraining.domain.InvoiceItem;


@Repository
public interface ItemRepository extends JpaRepository<InvoiceItem, Long> {


}
