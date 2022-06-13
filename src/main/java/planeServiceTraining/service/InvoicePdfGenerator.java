package planeServiceTraining.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@Component
public class InvoicePdfGenerator {

    public String generate() throws IOException, URISyntaxException {

        File fileOutput = new File("test.pdf");
        PDDocument doc = PDDocument.load(this.getClass().getClassLoader().getResourceAsStream("template.pdf"));

        PDPage firstPage = doc.getPage(0);
        PDPageContentStream cs = new PDPageContentStream(doc, firstPage, PDPageContentStream.AppendMode.APPEND, false, false);
        PDFont font = PDType0Font.load(doc, new File("arial.ttf"));
        setFontProperties(font, cs);
        fillText(cs, "test", 200);
        cs.close();
        doc.save(fileOutput);
        doc.close();

        return fileOutput.getAbsolutePath();
    }

    private void setFontProperties(PDFont font, PDPageContentStream contentStream) throws IOException {
        contentStream.setStrokingColor(Color.BLACK);
        contentStream.setNonStrokingColor(Color.BLACK);
        contentStream.fillAndStroke();
        contentStream.setFont(font, 12);
    }

    private void fillText(PDPageContentStream contentStream, String text, int y) throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(80, y);
        contentStream.showText(text);
        contentStream.newLine();
        contentStream.endText();
    }
}
