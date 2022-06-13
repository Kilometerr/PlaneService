package planeServiceTraining.reposotory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import planeServiceTraining.domain.Invoice;
import planeServiceTraining.domain.InvoiceStatus;


import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByInvoiceStatus(InvoiceStatus invoiceStatus);

}