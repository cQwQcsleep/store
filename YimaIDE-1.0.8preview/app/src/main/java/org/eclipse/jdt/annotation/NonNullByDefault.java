package org.eclipse.jdt.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Documented
@Retention(RetentionPolicy.CLASS)
public @interface NonNullByDefault {
    DefaultLocation[] value() default {DefaultLocation.PARAMETER, DefaultLocation.RETURN_TYPE, DefaultLocation.FIELD, DefaultLocation.TYPE_BOUND, DefaultLocation.TYPE_ARGUMENT, DefaultLocation.RECORD_COMPONENT};
}
