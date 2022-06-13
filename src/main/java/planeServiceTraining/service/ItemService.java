package planeServiceTraining.service;

import org.springframework.stereotype.Service;
import planeServiceTraining.domain.InvoiceItem;
import planeServiceTraining.reposotory.ItemRepository;
import planeServiceTraining.web.dto.ItemDTO;


@Service
public class ItemService {

    private ItemRepository itemRepository;

    public InvoiceItem createNewItem(InvoiceItem invoiceItem, ItemDTO itemDTO){
        InvoiceItem itemToAdd = invoiceItem.addNewItem(itemDTO);
        return itemRepository.save(itemToAdd);
    }
}
