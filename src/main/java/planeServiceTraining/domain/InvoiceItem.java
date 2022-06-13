package planeServiceTraining.domain;

import org.springframework.beans.factory.annotation.Value;
import planeServiceTraining.web.dto.ItemDTO;


import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Entity
@Table(name = "invoice_items")
public class InvoiceItem extends AbstractEntity {

    private static final long serialVersionUID = 560115371151345083L;

    public String name;
    public int quantity;
    public BigDecimal grossPrice;
    public BigDecimal taxRate;
    public String itemType;
    public BigDecimal netPrice;
    @ManyToMany
    @JoinTable(
            name = "items_invoices",
            joinColumns = @JoinColumn(name = "invoice_item_id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_id"))
    public List<Invoice> invoices;

    @Value("${invoice.item.tax.rate.new}")
    private BigDecimal taxRateNewItem;
    @Value("${invoice.item.tax.rate.used}")
    private BigDecimal taxRateUsedItem;

    public InvoiceItem() {
    }

    public InvoiceItem(String name, String itemType, int quantity,
                       BigDecimal grossPrice, BigDecimal netPrice, BigDecimal taxRate) {
        this.name = name;
        this.quantity = quantity;
        this.grossPrice = grossPrice;
        this.taxRate = taxRate;
        this.itemType = itemType;
        this.netPrice = netPrice;
    }

    public String getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }


    public BigDecimal getGrossPrice() {
        return grossPrice;
    }


    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

//    public InvoiceItem addNewItem(ItemDTO itemDTO) {
//        InvoiceItem invoiceItem = new InvoiceItem();
//        invoiceItem.setItemType(itemDTO.getType());
//        invoiceItem.setQuantity(itemDTO.getQuantity());
//        invoiceItem.setName(itemDTO.getName());
//        invoiceItem.setTaxRate(calculateTaxRate(itemDTO));
//        invoiceItem.setGrossPrice(calulateItemTax(itemDTO.getGrossPrice(), itemDTO));
//
//        return invoiceItem;
//    }
    public InvoiceItem addNewItem(ItemDTO itemDTO) {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setItemType(itemDTO.getType());
        invoiceItem.setQuantity(itemDTO.getQuantity());
        invoiceItem.setName(itemDTO.getName());
        invoiceItem.setTaxRate(calculateTaxRate(itemDTO));
        invoiceItem.setGrossPrice(calulateItemTax(itemDTO.getGrossPrice(), itemDTO));

        return invoiceItem;
    }

    public BigDecimal calculateTaxRate(ItemDTO itemDTO) {
        if (itemDTO.getType().equals((InvoiceItemType.NEW))) {
            return taxRateNewItem;
        } else {
            return taxRateUsedItem;
        }
    }

    public BigDecimal calulateItemTax(BigDecimal grossPrice, ItemDTO itemDTO) {
        return netPrice.multiply(calculateTaxRate(itemDTO)).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
    }


}
