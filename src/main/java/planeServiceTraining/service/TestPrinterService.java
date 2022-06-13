package planeServiceTraining.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import planeServiceTraining.domain.Invoice;
import planeServiceTraining.domain.exceptions.NoPrinterFound;


import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.PrinterJob;
import java.util.Arrays;

@Component
@Profile("dev")
public class TestPrinterService implements PrinterService {

//    @Value("${printer}")
    private String printerName = "printer";

    @Override
    public void print(Invoice invoice) {
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;

        PrintService printService = Arrays.stream(PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.PDF, null))
                .filter(printer -> printer.getName().equals(printerName))
                .findFirst()
                .orElseThrow(() -> new NoPrinterFound("cant find printer " + printerName));

        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintService(printService);
            PDDocument doc = PDDocument.load(this.getClass().getClassLoader().getResourceAsStream("template.pdf"));
            PDFPrintable printable = new PDFPrintable(doc);
            job.setPrintable(printable);
            job.print();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("s");

    }
}
