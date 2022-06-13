package planeServiceTraining.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceDTO {
    private String number;
    private LocalDate date;
    private BigDecimal grossPrice;

    public InvoiceDTO() {
    }

    public InvoiceDTO(String number, LocalDate date, BigDecimal grossPrice) {
        this.number = number;
        this.date = date;
        this.grossPrice = grossPrice;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }
}
