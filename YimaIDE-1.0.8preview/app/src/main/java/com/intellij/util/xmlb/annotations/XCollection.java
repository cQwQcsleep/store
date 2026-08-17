package com.intellij.util.xmlb.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XCollection {

    public enum Style {
        v1,
        v2
    }

    String elementName() default "option";

    Class<?>[] elementTypes() default {};

    String propertyElementName() default "";

    Style style() default Style.v1;

    String valueAttributeName() default "value";
}
