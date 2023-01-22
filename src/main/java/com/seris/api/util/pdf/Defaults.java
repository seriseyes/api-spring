package com.seris.api.util.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.seris.api.util.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Defaults
 *
 * @author Баярхүү.Лув, 2021.12.23 09:20
 */
public class Defaults {

    /**
     * Хоосон A4 PDF файл авах
     *
     * @return A4 pdf файл
     */
    public static File getA4Blank() {
        return new File(Constants.path_pdf + "/A4.pdf");
    }

    /**
     * @param pdfName      Хэвлэх маягтын нэр
     * @param pageTemplate Хуувасны хэмжээ
     * @return стандарт хэмжээстэй iText document
     */
    public static StatefulDocument getDocument(String pdfName, PageTemplate pageTemplate) {
        return init(pdfName, pageTemplate, null);
    }

    /**
     * @param pdfName      Хэвлэх маягтын нэр
     * @param pageTemplate Хуудасны хэмжээ
     * @param pdf          Дурын pdf Файл
     * @return дурын pdf файл template болгож ашиглах
     */
    public static StatefulDocument getDocument(String pdfName, PageTemplate pageTemplate, File pdf) {
        return init(pdfName, pageTemplate, pdf);
    }

    private static StatefulDocument init(String pdfName, PageTemplate pageTemplate, File pdf) {
        File tempFolder = new File(Constants.path_temp);
        if (!tempFolder.exists()) tempFolder.mkdir();

        String randomName = Constants.path_temp + "/" + UUID.randomUUID() + "-" + pdfName.replaceAll(" ", "-") + ".pdf";
        StatefulDocument document = new StatefulDocument(pageTemplate.getTemplate(), -25, -25, 36, 25, randomName);
        try {
            PdfReader reader = new PdfReader(pdf != null ? pdf.toURI().toString() : getA4Blank().toURI().toString());
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(randomName));
            document.addTitle(pdfName);
            document.addAuthor("NUM - Hospital");
            document.addCreator("NUM - Hospital | 2022 оны төгсөгч КУ - Баярхүү.Лув");
            document.addCreationDate();
            document.open();
            PdfContentByte canvas = writer.getDirectContent();
            document.setCanvas(canvas);
            document.setWriter(writer);
            PdfImportedPage page = writer.getImportedPage(reader, 1);
            canvas.addTemplate(page, 1f, 0, 0, 1f, 0, 0);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
