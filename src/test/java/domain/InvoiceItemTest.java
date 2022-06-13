package domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import planeServiceTraining.domain.InvoiceItem;
import planeServiceTraining.web.dto.ItemDTO;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class InvoiceItemTest {

    @Mock
    InvoiceItem invoiceItem;
    @Mock
    ItemDTO itemDTO;
    @Value("${invoice.item.tax.rate.new}")
    private BigDecimal taxRateNewItem;
    @Value("${invoice.item.tax.rate.used}")
    private BigDecimal taxRateUsedItem;

    @Test
    void calculatedTaxShouldReturnNewTax(){
        //given
        ItemDTO dto = mock(ItemDTO.class);
        dto.setType("NEW");

        //when
        BigDecimal result = invoiceItem.calculateTaxRate(dto);

        //then
        assertEquals(taxRateNewItem ,result);
    }
    @Test
    void calculatedTaxShouldReturnUsedTax(){
        //given
        ItemDTO dto = new ItemDTO();
        dto.setType("Used");

        //when
        BigDecimal result = invoiceItem.calculateTaxRate(dto);

        //then
        assertEquals(taxRateNewItem ,result);
    }


    @Test
    void shouldMakeIvoiceItem(){
        //given
        ItemDTO dto = new ItemDTO("NAME",
                "NEW",
                18239,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(80));

        //when
        invoiceItem.addNewItem(dto);

        //then
        verify(invoiceItem).addNewItem(dto);
    }

}