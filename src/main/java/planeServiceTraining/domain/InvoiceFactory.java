package planeServiceTraining.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import planeServiceTraining.domain.exceptions.CantCreateInvoiceNumber;
import planeServiceTraining.util.StringManipulator;
import planeServiceTraining.web.dto.InvoiceDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneOffset;

@Component
public class InvoiceFactory {


    private BigDecimal TaxRate;

    private String Prefix;

    public InvoiceFactory(@Value("$(invoice.tax.rate)")BigDecimal taxRate,
                          @Value("$(invoice.number.prefix)") String prefix) {
        TaxRate = taxRate;
        Prefix = prefix;
    }

    public Invoice createNew(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setDate(dto.getDate());
        try {
            invoice.setNumber(StringManipulator.createInvoiceNumber('-',
                    Prefix,
                    dto.getDate().toString(),
                    String.valueOf(dto.getDate().atStartOfDay().toEpochSecond(ZoneOffset.UTC))));
            invoice.setInvoiceStatus(InvoiceStatus.NEW);
            invoice.setTaxRate(TaxRate);
            invoice.setGrossPrice(dto.getGrossPrice());
            invoice.setTax(calculateTax(dto.getGrossPrice()));
            invoice.setNetPrice(invoice.getGrossPrice().subtract(invoice.getTax()));
            return invoice;
        } catch (CantCreateInvoiceNumber e) {
            e.printStackTrace();
            return null;
        }

    }

    private BigDecimal calculateTax(BigDecimal grossPrice) {
        return grossPrice.multiply(TaxRate)
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
    }

}
