package com.intellij.util.xmlb.annotations;

import com.intellij.util.xmlb.Converter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptionTag {
    Class<? extends Converter> converter() default Converter.class;

    String nameAttribute() default "name";

    String tag() default "option";

    String value() default "";

    String valueAttribute() default "value";
}
