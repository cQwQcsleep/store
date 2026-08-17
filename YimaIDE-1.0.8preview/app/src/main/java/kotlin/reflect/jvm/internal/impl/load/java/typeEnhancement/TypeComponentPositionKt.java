package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class TypeComponentPositionKt {
    public static final boolean shouldEnhance(TypeComponentPosition typeComponentPosition) {
        typeComponentPosition.getClass();
        return typeComponentPosition != TypeComponentPosition.INFLEXIBLE;
    }
}
