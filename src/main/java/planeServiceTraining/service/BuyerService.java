package planeServiceTraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import planeServiceTraining.domain.Buyer;
import planeServiceTraining.domain.Invoice;
import planeServiceTraining.domain.exceptions.CantFindInvoiceException;
import planeServiceTraining.reposotory.BuyerRepository;
import planeServiceTraining.reposotory.InvoiceRepository;
import planeServiceTraining.web.dto.BuyerDTO;

import java.util.List;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    //Zwróć defaultowy "pusty" obiekt Buyer
    public Buyer getBuyerById(@NonNull Long id) {
        return buyerRepository.findById(id)
                .orElse(new Buyer());
    }

    public Buyer addNew(BuyerDTO dto) {
        Buyer buyerToAdd = new Buyer(dto.getName(), dto.getAddress(), null);
        return buyerRepository.save(buyerToAdd);
    }

    public Buyer update(BuyerDTO dto, Long id) {
        Buyer buyerToUpdate = getBuyerById(id);
        buyerToUpdate.setAddress(dto.getAddress());
        buyerToUpdate.setName(dto.getName());
        buyerRepository.save(buyerToUpdate);
        return buyerToUpdate;
    }

    public void deleteBuyer(Buyer buyer) {
        buyerRepository.delete(buyer);
    }

    public void deleteBuyer(Long id) {
        buyerRepository.deleteById(id);
    }

    public Buyer addInvoice(Long buyerId, Long invoiceId) {
        Buyer buyer = getBuyerById(buyerId);
        Invoice invoice = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new CantFindInvoiceException(String.format("invoice %d not found", invoiceId)));
        buyer.addInvoice(invoice);
        return buyerRepository.save(buyer);
    }
}
