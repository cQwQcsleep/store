package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.ir.symbols.IrBindableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
public final class SymbolTableExtension$reference$4 implements Function0 {
    final /* synthetic */ Object $declaration;
    final /* synthetic */ Function1 $privateSymbolFactory;

    public SymbolTableExtension$reference$4(Function1 function1, Object obj) {
        this.$privateSymbolFactory = function1;
        this.$declaration = obj;
    }

    public final IrBindableSymbol invoke() {
        return (IrBindableSymbol) this.$privateSymbolFactory.invoke(this.$declaration);
    }
}
