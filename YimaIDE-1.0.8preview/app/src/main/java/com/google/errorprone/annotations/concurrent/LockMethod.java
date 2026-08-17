package com.google.errorprone.annotations.concurrent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Deprecated
public @interface LockMethod {
    String[] value();
}
