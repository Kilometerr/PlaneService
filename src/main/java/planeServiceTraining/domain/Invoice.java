package planeServiceTraining.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice extends AbstractEntity{

    private static final long serialVersionUID = 560115371151345084L;

    private String number;
    private LocalDate date;
    private BigDecimal netPrice;
    private BigDecimal grossPrice;
    private BigDecimal tax;
    private BigDecimal taxRate;
    @ManyToMany
    @JoinTable(
            name = "items_invoices",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "invoice_item_id"))
    private List<InvoiceItem> items;
    @Enumerated(value = EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    public Invoice() {
        this.invoiceStatus = InvoiceStatus.NEW;
    }

    public Invoice(String number, LocalDate date,
                   BigDecimal netPrice, BigDecimal grossPrice,
                   BigDecimal tax, BigDecimal taxRate,
                   List<InvoiceItem> items, InvoiceStatus invoiceStatus) {
        this.number = number;
        this.date = date;
        this.netPrice = netPrice;
        this.grossPrice = grossPrice;
        this.tax = tax;
        this.taxRate = taxRate;
        this.items = items;
        this.invoiceStatus = invoiceStatus;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public InvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public void setInvoiceStatus(InvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }
}
