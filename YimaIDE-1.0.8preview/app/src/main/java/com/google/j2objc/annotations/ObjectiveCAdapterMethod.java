package com.google.j2objc.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.CLASS)
public @interface ObjectiveCAdapterMethod {

    public enum Adaptation {
        EXCEPTIONS_AS_ERRORS,
        RETURN_NATIVE_BOOLS,
        ACCEPT_NATIVE_BOOLS,
        RETURN_NATIVE_ENUMS,
        ACCEPT_NATIVE_ENUMS,
        RETURN_ADAPTER_PROTOCOLS,
        RETURN_LISTS_AS_NATIVE_ARRAYS
    }

    Adaptation[] adaptations() default {};

    String selector() default "";
}
