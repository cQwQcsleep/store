package org.jetbrains.kotlin.ir.backend.js.utils;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"mutableReferenceOf", "Lorg/jetbrains/kotlin/ir/backend/js/utils/MutableReference;", "T", "value", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/ir/backend/js/utils/MutableReference;", "org.jetbrains.kotlin:backend.js"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ReferenceKt {
    public static final <T> MutableReference<T> mutableReferenceOf(T t) {
        return new MutableReference<>(t);
    }
}
