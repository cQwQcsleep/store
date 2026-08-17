package com.intellij.util.xmlb.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MapAnnotation {
    String entryTagName() default "entry";

    String keyAttributeName() default "key";

    boolean surroundKeyWithTag() default true;

    boolean surroundWithTag() default true;

    String valueAttributeName() default "value";
}
