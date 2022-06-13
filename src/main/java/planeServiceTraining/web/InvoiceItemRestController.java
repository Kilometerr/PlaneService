package planeServiceTraining.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import planeServiceTraining.domain.InvoiceItem;
import planeServiceTraining.service.ItemService;
import planeServiceTraining.web.dto.ItemDTO;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/invoices")
public class InvoiceItemRestController {
    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/get")
    private List<InvoiceItem> getAllInvoiceItems() {
        return new ArrayList<>();
    }

    @PostMapping(value = "/add")
    private InvoiceItem createNewItem(InvoiceItem invoiceItem, @RequestBody ItemDTO itemDTO) {
        return itemService.createNewItem(invoiceItem, itemDTO);
    }
}
