package org.jetbrains.kotlin.library.components;

import kotlin.Metadata;
import org.jetbrains.kotlin.library.Klib;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\"\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0016\u0010\u0007\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006\t"}, d2 = {"inlinableFunctionsIr", "Lorg/jetbrains/kotlin/library/components/KlibIrComponent;", "Lorg/jetbrains/kotlin/library/Klib;", "getInlinableFunctionsIr", "(Lorg/jetbrains/kotlin/library/Klib;)Lorg/jetbrains/kotlin/library/components/KlibIrComponent;", "ir", "getIr", "irOrFail", "getIrOrFail", "kotlin-util-klib"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KlibIrComponentKt {
    public static final KlibIrComponent getInlinableFunctionsIr(Klib klib) {
        klib.getClass();
        return (KlibIrComponent) klib.getComponent(KlibIrComponent.Kind.InlinableFunctions.INSTANCE);
    }

    public static final KlibIrComponent getIr(Klib klib) {
        klib.getClass();
        return (KlibIrComponent) klib.getComponent(KlibIrComponent.Kind.Main.INSTANCE);
    }

    public static final KlibIrComponent getIrOrFail(Klib klib) {
        klib.getClass();
        KlibIrComponent klibIrComponent = (KlibIrComponent) klib.getComponent(KlibIrComponent.Kind.Main.INSTANCE);
        if (klibIrComponent != null) {
            return klibIrComponent;
        }
        f2f.a("No 'ir' component in library ", klib.getLocation());
        return null;
    }
}
