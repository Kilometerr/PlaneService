package planeServiceTraining.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("prod")
public class InvoiceFactoryProdConfiguration {

    @Value("${invoice.tax.rate}")
    private BigDecimal taxRate;

    @Value("${invoice.number.prefix}")
    private String prefix;

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public String getPrefix() {
        return prefix;
    }
}
