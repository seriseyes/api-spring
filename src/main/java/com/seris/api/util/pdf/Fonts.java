package com.seris.api.util.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.seris.api.util.Constants;

/**
 * Itext font авхад ашиглах
 *
 * @author Баярхүү.Лув, 2021.12.23 09:05
 */
public class Fonts {

    /**
     * Font төрөл ба хэмжээгээр авах
     *
     * @param fontType @see FontType.java
     * @param size     font-ны хэмжээ
     * @return iText Font
     */
    public static Font get(FontType fontType, float size) {
        return FontFactory.getFont(Constants.path_font + "/" + fontType.getFontName(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size);
    }

    /**
     * Font төрөл, хэмжээгээр ба өнгөтэй авах
     *
     * @param fontType @see FontType.java
     * @param size     font-ны хэмжээ
     * @param color    өнгө
     * @return iText Font
     */
    public static Font get(FontType fontType, float size, BaseColor color) {
        return FontFactory.getFont(Constants.path_font + "/" + fontType.getFontName(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED, size, -1, color);
    }
}
