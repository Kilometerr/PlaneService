package planeServiceTraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import planeServiceTraining.domain.Buyer;
import planeServiceTraining.service.BuyerService;
import planeServiceTraining.web.dto.BuyerDTO;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BuyerRestController {

    @Autowired
    private BuyerService buyerService;

    @GetMapping(value = "/buyer/all")
    public List<Buyer> getAllBuyers() {
        return buyerService.getAllBuyers();
    }

    @GetMapping(value = "/buyer/{id}")
    public Buyer getSingleBuyer(@PathVariable("id") Long id) {
        return buyerService.getBuyerById(id);
    }

    //.../api/v1/buyer?id=1
    @GetMapping(value = "/buyer")
    public Buyer getSingleBuyerByVariable(@RequestParam("id") Long id) {
        return buyerService.getBuyerById(id);
    }

    @PostMapping(value = "/buyer/add")
    public Buyer addNewBuyer(@RequestBody BuyerDTO buyerDTO) {
        return buyerService.addNew(buyerDTO);
    }

    @PutMapping(value = "/buyer/update")
    public Buyer updateBuyer(@RequestBody BuyerDTO buyerDTO, @RequestParam("id") Long id) {
        return buyerService.update(buyerDTO, id);
    }

    @PutMapping("/buyer/add/invoice")
    public Buyer addInvoice(@RequestParam("buyerId") Long buyerId, @RequestParam("invoiceId") Long invoiceId) {
        return buyerService.addInvoice(buyerId, invoiceId);
    }


}
