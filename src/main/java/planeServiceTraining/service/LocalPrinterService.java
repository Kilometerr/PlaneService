package planeServiceTraining.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import planeServiceTraining.domain.Invoice;


@Component
@Profile("prod")
public class LocalPrinterService implements PrinterService {

    @Override
    public void print(Invoice invoice) {
        //TODO
    }
}
