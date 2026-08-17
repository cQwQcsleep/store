package org.jetbrains.kotlin.library;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\u001a\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"klibAttribute", "Lorg/jetbrains/kotlin/library/KlibAttribute$Delegate;", "T", "", "klibFlag", "Lorg/jetbrains/kotlin/library/KlibAttribute$Flag$Delegate;", "kotlin-util-klib"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KlibAttributesKt {
    public static final <T> KlibAttribute.Delegate<T> klibAttribute() {
        return new KlibAttribute.Delegate<>();
    }

    public static final KlibAttribute.Flag.Delegate klibFlag() {
        return new KlibAttribute.Flag.Delegate();
    }
}
