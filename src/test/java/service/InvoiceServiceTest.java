package service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import planeServiceTraining.domain.Invoice;
import planeServiceTraining.domain.InvoiceFactory;
import planeServiceTraining.reposotory.InvoiceRepository;
import planeServiceTraining.service.InvoiceService;
import planeServiceTraining.service.PrinterService;
import planeServiceTraining.web.dto.InvoiceDTO;


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;
    @Spy
    private PrinterService printerService;
    @Spy
    private InvoiceFactory invoiceFactory = new InvoiceFactory(BigDecimal.TEN,"TEST");

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    void shouldPrint(){
        //given
        Invoice invoice = mock(Invoice.class);

        when(invoice.getNumber()).thenReturn("3");

        //when
        invoiceService.printInvoice(invoice);

        //then
        verify(printerService, times(1)).print(invoice);
    }

    @Test
    void shouldCreateAndSaveInvoice(){
        //given
        InvoiceDTO dto = new InvoiceDTO("3", LocalDate.now(), BigDecimal.valueOf(100));

        //when
        invoiceService.createNewInvoice(dto);

        //then
        verify(invoiceFactory).createNew(dto);
        verify(invoiceRepository).save(any());
    }

}