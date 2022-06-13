package planeServiceTraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import planeServiceTraining.domain.Invoice;
import planeServiceTraining.service.InvoiceService;
import planeServiceTraining.web.dto.InvoiceDTO;


import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class InvoiceRestController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(value = "/invoices")
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAll();
    }

    @PostMapping(value = "/invoice/add")
    public Invoice addNewInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.createNewInvoice(invoiceDTO);
    }


}
