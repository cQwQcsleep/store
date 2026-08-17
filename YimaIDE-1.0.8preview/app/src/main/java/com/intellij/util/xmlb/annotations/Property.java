package com.intellij.util.xmlb.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {

    public enum Style {
        OPTION_TAG,
        ATTRIBUTE
    }

    boolean assertIfNoBindings() default true;

    boolean flat() default false;

    Style style() default Style.OPTION_TAG;

    boolean surroundWithTag() default true;
}
