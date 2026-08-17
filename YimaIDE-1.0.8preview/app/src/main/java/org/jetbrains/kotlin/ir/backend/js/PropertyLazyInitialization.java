package org.jetbrains.kotlin.ir.backend.js;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/PropertyLazyInitialization;", "", "enabled", "", "eagerInitialization", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "<init>", "(ZLorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;)V", "getEnabled", "()Z", "getEagerInitialization", "()Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PropertyLazyInitialization {
    private final IrClassSymbol eagerInitialization;
    private final boolean enabled;

    public PropertyLazyInitialization(boolean z, IrClassSymbol irClassSymbol) {
        irClassSymbol.getClass();
        this.enabled = z;
        this.eagerInitialization = irClassSymbol;
    }

    public final IrClassSymbol getEagerInitialization() {
        return this.eagerInitialization;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }
}
