package org.jetbrains.kotlin.load.java.typeEnhancement;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"shouldEnhance", "", "Lorg/jetbrains/kotlin/load/java/typeEnhancement/TypeComponentPosition;", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class TypeComponentPositionKt {
    public static final boolean shouldEnhance(TypeComponentPosition typeComponentPosition) {
        typeComponentPosition.getClass();
        return typeComponentPosition != TypeComponentPosition.INFLEXIBLE;
    }
}
