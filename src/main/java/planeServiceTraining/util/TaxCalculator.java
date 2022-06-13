package planeServiceTraining.util;


import planeServiceTraining.domain.InvoiceItem;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class TaxCalculator {

    private static final MathContext MATH_CONTEXT = new MathContext(2, RoundingMode.HALF_UP);

    public static BigDecimal calculateTax(InvoiceItem invoiceItem) {
        if (invoiceItem.taxRate.compareTo(BigDecimal.ZERO) == 0) {
            return invoiceItem.getGrossPrice();
        }

        return invoiceItem.getGrossPrice().divide(invoiceItem.taxRate, MATH_CONTEXT);
    }

    public static BigDecimal calculateTax(List<InvoiceItem> invoiceItem) {
        return invoiceItem.stream()
                .map(TaxCalculator::calculateTax)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .round(MATH_CONTEXT);
    }
}
