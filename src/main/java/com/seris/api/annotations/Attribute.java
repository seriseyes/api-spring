package com.seris.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Attribute {
    String value();//Нэр

    String descDev() default "";//Хөгжүүлэгч нарт зориулж тайлбар бичих

    boolean skip() default false;//@NotNull, @NotEmpty, @Length гэх мэт annotation-тай байсан ч  шалгахгүй алгасах

    boolean trim() default false;//String.trim() method ашиглах

    boolean noSpace() default false;//Бүх space-ийг (зайг) устгах

    boolean capitalize() default false;//Эхний үсгийг том болгох

    boolean upper() default false;//Бүх үсгийг том болгох

    boolean lower() default false;//Бүх үсгийг жижиг болгох

    boolean notZero() default false;//0 биш байх
}
