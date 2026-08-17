package com.intellij.util.xmlb.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Deprecated
public @interface AbstractCollection {
    String elementTag() default "option";

    Class[] elementTypes() default {};

    String elementValueAttribute() default "value";

    boolean sortOrderedSet() default true;

    boolean surroundWithTag() default true;
}
