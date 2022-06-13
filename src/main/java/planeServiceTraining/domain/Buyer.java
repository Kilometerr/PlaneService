package planeServiceTraining.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Buyer extends AbstractEntity {

    private static final long serialVersionUID = 560115371151345082L;

    public String name;
    public String address;

    @OneToMany
    @JoinColumn(name = "invoice_id")
    public List<Invoice> invoices;

    public Buyer() {
    }

    public Buyer(String name, String address, List<Invoice> invoices) {
        this.name = name;
        this.address = address;
        this.invoices = invoices;
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
