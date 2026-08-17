package org.eclipse.jdt.internal.compiler.env;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface IBinaryAnnotation extends IBinaryInfo {
    IBinaryElementValuePair[] getElementValuePairs();

    char[] getTypeName();

    default boolean isDeprecatedAnnotation() {
        return false;
    }

    default boolean isExternalAnnotation() {
        return false;
    }
}
