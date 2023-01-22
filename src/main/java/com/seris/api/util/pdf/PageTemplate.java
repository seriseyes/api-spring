package com.seris.api.util.pdf;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

/**
 * Хуудасны хэмжээ тодорхойлох класс
 *
 * @author Баярхүү.Лув, 2021.12.23 09:20
 * @see PageSize
 */
public enum PageTemplate {
    A4(PageSize.A4),
    A4rotate(PageSize.A4.rotate());

    private final Rectangle template;

    PageTemplate(Rectangle template) {
        this.template = template;
    }

    public Rectangle getTemplate() {
        return template;
    }
}
