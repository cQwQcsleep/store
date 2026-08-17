package com.sun.tools.javac.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface DefinedBy {

    public enum Api {
        ANNOTATION_PROCESSING("javax.annotation.processing"),
        COMPILER("javax.tools"),
        COMPILER_TREE("com.sun.source"),
        LANGUAGE_MODEL("javax.lang.model");

        public final String packageRoot;

        Api(String str) {
            this.packageRoot = str;
        }
    }

    Api value();
}
