package org.jetbrains.kotlin.backend.wasm.lower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\b\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0004\b\u0006\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/lower/PropertyReferencesConstructorsSet;", "", "local", "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "byReceiversCount", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;Ljava/util/List;)V", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "(Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;Ljava/util/List;)V", "getLocal", "()Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "getByReceiversCount", "()Ljava/util/List;", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PropertyReferencesConstructorsSet {
    private final List<IrConstructorSymbol> byReceiversCount;
    private final IrConstructorSymbol local;

    public PropertyReferencesConstructorsSet(IrClassSymbol irClassSymbol, List<? extends IrClassSymbol> list) {
        irClassSymbol.getClass();
        list.getClass();
        IrConstructorSymbol irConstructorSymbol = (IrConstructorSymbol) SequencesKt.single(IrUtilsKt.getConstructors(irClassSymbol));
        List<? extends IrClassSymbol> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((IrConstructorSymbol) SequencesKt.single(IrUtilsKt.getConstructors((IrClassSymbol) it.next())));
        }
        this(irConstructorSymbol, arrayList);
    }

    public final List<IrConstructorSymbol> getByReceiversCount() {
        return this.byReceiversCount;
    }

    public final IrConstructorSymbol getLocal() {
        return this.local;
    }

    public PropertyReferencesConstructorsSet(IrConstructorSymbol irConstructorSymbol, List<? extends IrConstructorSymbol> list) {
        irConstructorSymbol.getClass();
        list.getClass();
        this.local = irConstructorSymbol;
        this.byReceiversCount = list;
    }
}
