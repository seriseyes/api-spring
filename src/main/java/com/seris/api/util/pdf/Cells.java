package com.seris.api.util.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;

import java.io.IOException;
import java.io.Serializable;

/**
 * IText-ын Cell-г өргөтгөв
 *
 * @author Л. Баярхүү, 2020.12.29
 */
public class Cells extends PdfPCell {
    /**
     * Default font
     */
    private static final Font defaultFont = Fonts.get(FontType.arial, 10);

    protected Cells(String value) {
        setPhrase(new Phrase(value, defaultFont));
        setBorder(-1);
    }

    protected Cells(String value, Font font) {
        setPhrase(new Phrase(value, font));
        setBorder(-1);
    }

    /**
     * Эхлэл (empty)
     *
     * @return Cells
     */
    public static Cells get() {
        return new Cells("");
    }

    /**
     * Эхлэл (String)
     *
     * @param value text
     * @return Cells
     */
    public static Cells get(String value) {
        return new Cells(value);
    }

    /**
     * Эхлэл (StringBuilder)
     *
     * @param value text
     * @return Cells
     */
    public static Cells get(StringBuilder value) {
        return new Cells(value.toString());
    }

    /**
     * Эхлэл (int)
     *
     * @param value text
     * @return Cells
     */
    public static Cells get(int value) {
        return new Cells(String.valueOf(value));
    }

    /**
     * Эхлэл (double)
     *
     * @param value text
     * @return Cells
     */
    public static Cells get(double value) {
        return new Cells(String.valueOf(value));
    }

    /**
     * Эхлэл
     * ? : operator expression харуулах
     *
     * @param serializable ? : expression
     * @return Cells
     */
    public static Cells get(Serializable serializable) {
        return new Cells(serializable == null ? "" : serializable.toString());
    }

    /**
     * Эхлэл (String)
     *
     * @param value text
     * @param font  font
     * @return Cells
     */
    public static Cells get(String value, Font font) {
        return new Cells(value, font);
    }

    /**
     * Эхлэл (StringBuilder)
     *
     * @param value text
     * @param font  font
     * @return Cells
     */
    public static Cells get(StringBuilder value, Font font) {
        return new Cells(value.toString(), font);
    }

    /**
     * Эхлэл (int)
     *
     * @param value text
     * @param font  font
     * @return Cells
     */
    public static Cells get(int value, Font font) {
        return new Cells(String.valueOf(value), font);
    }

    /**
     * Эхлэл (double)
     *
     * @param value text
     * @param font  font
     * @return Cells
     */
    public static Cells get(double value, Font font) {
        return new Cells(String.valueOf(value), font);
    }

    /**
     * Эхлэл
     * ? : operator expression харуулах
     *
     * @param serializable ? : expression
     * @param font         font
     * @return Cells
     */
    public static Cells get(Serializable serializable, Font font) {
        return new Cells(serializable.toString(), font);
    }

    /**
     * Зураг
     *
     * @param path   Hard disk дээрх байршил
     * @param width  зурагны урт
     * @param height зурагны өндөр
     */
    public static PdfPCell getImageCell(String path, float width, float height) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(0);
        if (path != null && !path.isEmpty()) {
            Image image;
            try {
                image = Image.getInstance(path);
                image.scaleToFit(width, height);
            } catch (BadElementException | IOException e) {
                return cell;
            }
            cell.addElement(image);
        }
        return cell;
    }

    /**
     * Одоо байгаа text дээр нь залгах (String)
     *
     * @param value text
     * @return Cells
     */
    public Cells add(String value) {
        getPhrase().add(new Phrase(value, defaultFont));
        return this;
    }

    /**
     * Одоо байгаа text дээр нь залгах (int)
     *
     * @param value text
     * @return Cells
     */
    public Cells add(int value) {
        getPhrase().add(new Phrase(String.valueOf(value), defaultFont));
        return this;
    }

    /**
     * Одоо байгаа text дээр нь залгах (double)
     *
     * @param value text
     * @return Cells
     */
    public Cells add(double value) {
        getPhrase().add(new Phrase(String.valueOf(value), defaultFont));
        return this;
    }

    /**
     * Одоо байгаа text дээр нь залгах (String)
     *
     * @param value text
     * @param font  font
     * @return Cells
     */
    public Cells add(String value, Font font) {
        getPhrase().add(new Phrase(value, font));
        return this;
    }

    /**
     * Одоо байгаа text дээр нь залгах (int)
     *
     * @param value text
     * @param font  font
     * @return Cells
     */
    public Cells add(int value, Font font) {
        getPhrase().add(new Phrase(String.valueOf(value), font));
        return this;
    }

    /**
     * Одоо байгаа text дээр нь залгах (double)
     *
     * @param value text
     * @param font  font
     * @return Cells
     */
    public Cells add(double value, Font font) {
        getPhrase().add(new Phrase(String.valueOf(value), font));
        return this;
    }

    /**
     * Эргүүлэх
     *
     * @param rotation хэдэн градус эргүүлэх
     * @return Cells
     */
    public Cells withRotation(int rotation) {
        setRotation(rotation);
        return this;
    }

    /**
     * BaseColor enum-аар өгөх
     *
     * @param color өнгө
     * @return Cells
     */
    public Cells withColor(BaseColor color) {
        if (color != null) setBackgroundColor(color);
        return this;
    }

    /**
     * RGB ашиглаж өнгө өгөх
     *
     * @param RED   улаан (0-255)
     * @param GREEN ногоон (0-255)
     * @param BLUE  цэнхэр (0-255)
     * @return Cells
     */
    public Cells withColor(float RED, float GREEN, float BLUE) {
        setBackgroundColor(new BaseColor(RED, GREEN, BLUE, 1.0f));
        return this;
    }

    /**
     * RGBA ашиглаж өнгө өгөх
     *
     * @param RED   улаан (0-255)
     * @param GREEN ногоон (0-255)
     * @param BLUE  цэнхэр (0-255)
     * @param ALPHA alpha (0-1)
     * @return Cells
     */
    public Cells withColor(float RED, float GREEN, float BLUE, float ALPHA) {
        setBackgroundColor(new BaseColor(RED, GREEN, BLUE, ALPHA));
        return this;
    }

    /**
     * Багнын дагуу хэд хуваахаа өгөх
     *
     * @param colspan баганын тоо
     * @return Cells
     */
    public Cells withColspan(int colspan) {
        setColspan(colspan);
        return this;
    }

    /**
     * Мөрийн дагуу хэд хуваахаа өгөх
     *
     * @param rowspan мөрийн тоо
     * @return Cells
     */
    public Cells withRowspan(int rowspan) {
        setRowspan(rowspan);
        return this;
    }

    /**
     * Мөр баганыг хэд хуваахаа өгөх
     *
     * @param colspan баганы тоо
     * @param rowspan мөрын тоо
     * @return Cells
     */
    public Cells withSpans(int rowspan, int colspan) {
        setRowspan(rowspan);
        setColspan(colspan);
        return this;
    }

    /**
     * Border-той болгох
     *
     * @return Cells
     */
    public Cells withBorder() {
        setBorder(15);
        return this;
    }

    /**
     * Border өгөх
     *
     * @param width өргөн
     * @return Cells
     */
    public Cells withBorder(int width) {
        setBorder(width);
        return this;
    }

    /**
     * Зүүн дээд
     *
     * @return Cells
     */
    public Cells withAlignTopLeft() {
        setVerticalAlignment(Element.ALIGN_LEFT);
        return this;
    }

    /**
     * Дээд гол
     *
     * @return Cells
     */
    public Cells withAlignTopCenter() {
        setVerticalAlignment(Element.ALIGN_TOP);
        setHorizontalAlignment(Element.ALIGN_CENTER);
        return this;
    }

    /**
     * Баруун дээд
     *
     * @return Cells
     */
    public Cells withAlignTopRight() {
        setHorizontalAlignment(Element.ALIGN_RIGHT);
        return this;
    }

    /**
     * Зүүн голд
     *
     * @return Cells
     */
    public Cells withAlignMiddleLeft() {
        setHorizontalAlignment(Element.ALIGN_LEFT);
        setVerticalAlignment(Element.ALIGN_MIDDLE);
        return this;
    }

    /**
     * Төвд байрлуулах
     *
     * @return Cells
     */
    public Cells withAlignMiddleCenter() {
        setVerticalAlignment(Element.ALIGN_MIDDLE);
        setHorizontalAlignment(Element.ALIGN_CENTER);
        return this;
    }

    /**
     * Баруун голд
     *
     * @return Cells
     */
    public Cells withAlignMiddleRight() {
        setVerticalAlignment(Element.ALIGN_MIDDLE);
        setHorizontalAlignment(Element.ALIGN_RIGHT);
        return this;
    }

    /**
     * Зүүн доод
     *
     * @return Cells
     */
    public Cells withAlignBottomLeft() {
        setVerticalAlignment(Element.ALIGN_BOTTOM);
        return this;
    }

    /**
     * Доод голд
     *
     * @return Cells
     */
    public Cells withAlignBottomCenter() {
        setVerticalAlignment(Element.ALIGN_BOTTOM);
        setHorizontalAlignment(Element.ALIGN_CENTER);
        return this;
    }

    /**
     * Баруун доод
     *
     * @return Cells
     */
    public Cells withAlignBottomRight() {
        setVerticalAlignment(Element.ALIGN_BOTTOM);
        setHorizontalAlignment(Element.ALIGN_RIGHT);
        return this;
    }
}
