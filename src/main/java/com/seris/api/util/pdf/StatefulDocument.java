package com.seris.api.util.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;

/**
 * String state хадгалдаг iText Document
 *
 * @author Баярхүү.Лув, 2021.12.23 09:59
 */
public class StatefulDocument extends Document {
    private final String state;
    @Getter
    @Setter
    private PdfContentByte canvas;
    @Getter
    @Setter
    private PdfWriter writer;

    public StatefulDocument(Rectangle pageSize, float marginLeft, float marginRight, float marginTop, float marginBottom, String state) {
        super(pageSize, marginLeft, marginRight, marginTop, marginBottom);
        this.state = state;
    }

    public String getState() {
        close();
        return state;
    }
}
