package service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import planeServiceTraining.domain.Buyer;
import planeServiceTraining.domain.Invoice;
import planeServiceTraining.domain.exceptions.CantFindInvoiceException;
import planeServiceTraining.reposotory.BuyerRepository;
import planeServiceTraining.reposotory.InvoiceRepository;
import planeServiceTraining.service.BuyerService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuyerServiceTest {
    @Mock
    private BuyerRepository buyerRepository;
    @Mock
    private InvoiceRepository invoiceRepository;
    @InjectMocks
    private BuyerService buyerService;

    @Test
    void shouldGetBuyer() {
        //given
        Long id = 18L;

        Buyer expectedBuyer = new Buyer("Zbyszek", "Kolorowa 2", new ArrayList<>());

        when(buyerRepository.findById(id)).thenReturn(Optional.of(expectedBuyer));

        //when
        Buyer result = buyerService.getBuyerById(id);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Zbyszek");
        assertThat(result.getAddress()).isEqualTo("Kolorowa 2");
        assertThat(result.getInvoices()).isNotNull();
    }

    @Test
    void shouldThrowNullId() {
        //given
        Long id = null;

        Buyer expectedBuyer = new Buyer("Zbyszek", "Kolorowa 2", new ArrayList<>());

        when(buyerRepository.findById(id)).thenThrow(new IllegalArgumentException());

        //when
        Throwable result = catchThrowable(()->buyerService.getBuyerById(id));

        //then
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    void shouldAddInvoiceToBuyer(){
        //given
        Long buyerId = 1L;
        Buyer providedBuyer = new Buyer("Zbyszek", "Kolorowa 2", new ArrayList<>());
        Long invoiceId = 100L;
        Invoice expectedInvoice =  mock(Invoice.class);
        Buyer expectedBuyer = new Buyer("Zbyszek", "Kolorowa 2", Arrays.asList(expectedInvoice));

        ArgumentCaptor<Buyer> argumentCaptor = ArgumentCaptor.forClass(Buyer.class);

        when(buyerRepository.findById(buyerId)).thenReturn(Optional.of(providedBuyer));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(expectedInvoice));
        when(buyerRepository.save(argumentCaptor.capture())).thenReturn(any());

        //when
        buyerService.addInvoice(buyerId, invoiceId);

        //then
        assertThat(argumentCaptor.getValue()).isNotNull();
        assertThat(argumentCaptor.getValue().getInvoices().size()).isEqualTo(1);
        assertThat(argumentCaptor.getValue().getInvoices().get(0)).isEqualTo((expectedInvoice));
    }

    @Test
    void shouldThrowCantFindInvoice(){
        //given
        Long buyerId = 1L;
        Buyer providedBuyer = new Buyer("Zbyszek", "Kolorowa 2", new ArrayList<>());
        Long invoiceId = 100L;

        when(buyerRepository.findById(buyerId)).thenReturn(Optional.of(providedBuyer));
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.empty());

        //when
        Throwable result = catchThrowable(()->buyerService.addInvoice(buyerId, invoiceId));

        //then
        verify(buyerRepository,times(0)).save(any());
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(CantFindInvoiceException.class);
        assertThat(result.getMessage()).contains(String.valueOf(invoiceId));
    }



}