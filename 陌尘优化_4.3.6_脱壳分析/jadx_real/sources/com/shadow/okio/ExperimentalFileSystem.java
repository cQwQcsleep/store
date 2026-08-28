package com.shadow.okio;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: /workspace/unpacked/classes2.dex */
public @interface ExperimentalFileSystem {
}
