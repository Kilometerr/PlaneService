package planeServiceTraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import planeServiceTraining.domain.Invoice;
import planeServiceTraining.domain.InvoiceFactory;
import planeServiceTraining.domain.InvoiceStatus;
import planeServiceTraining.reposotory.InvoiceRepository;
import planeServiceTraining.web.dto.InvoiceDTO;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;
    private PrinterService printService;
    private InvoiceFactory invoiceFactory;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository,
                          PrinterService printService,
                          InvoiceFactory invoiceFactory) {
        this.invoiceRepository = invoiceRepository;
        this.printService = printService;
        this.invoiceFactory = invoiceFactory;
    }

    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No invoice found"));
    }

    public List<Invoice> getAllByStatus(InvoiceStatus status) {
        return invoiceRepository.findAllByInvoiceStatus(status);
    }

    public Invoice createNewInvoice(InvoiceDTO dto) {
        Invoice invoice = invoiceFactory.createNew(dto);
        return invoiceRepository.save(invoice);
    }

    public void printInvoice(Invoice invoice) {
        if (nonNull(invoice.getNumber())) printService.print(invoice);
    }
}
