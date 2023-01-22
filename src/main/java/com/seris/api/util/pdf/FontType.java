package com.seris.api.util.pdf;

/**
 * IText Font төрөл
 *
 * @author Баярхүү.Лув, 2021.12.23 09:20
 */
public enum FontType {
    arial("arial.ttf"),
    arialBold("arialbd.ttf"),
    arialItalic("ariali.ttf"),
    arialItalicBold("arialbi.ttf"),
    seguisym("seguisym.ttf"),//Symbols
    symbol("symbol.ttf"),//Symbols
    symbola("Symbola.ttf");//Symbols

    private final String fontName;

    FontType(String fontName) {
        this.fontName = fontName;
    }

    public String getFontName() {
        return fontName;
    }
}
