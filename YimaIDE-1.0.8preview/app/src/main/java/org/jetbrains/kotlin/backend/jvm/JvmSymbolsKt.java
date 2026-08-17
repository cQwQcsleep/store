package org.jetbrains.kotlin.backend.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0007"}, d2 = {"functionByName", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "name", "", "fieldByName", "Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "org.jetbrains.kotlin:backend.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmSymbolsKt {
    public static final IrFieldSymbol fieldByName(IrClassSymbol irClassSymbol, String str) {
        irClassSymbol.getClass();
        str.getClass();
        boolean z = false;
        Object obj = null;
        for (Object obj2 : IrUtilsKt.getFields(irClassSymbol)) {
            if (Intrinsics.areEqual(((IrFieldSymbol) obj2).getOwner().getName().asString(), str)) {
                if (z) {
                    w01.a("Sequence contains more than one matching element.");
                    return null;
                }
                z = true;
                obj = obj2;
            }
        }
        if (z) {
            return (IrFieldSymbol) obj;
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }

    public static final IrSimpleFunctionSymbol functionByName(IrClassSymbol irClassSymbol, String str) {
        irClassSymbol.getClass();
        str.getClass();
        boolean z = false;
        Object obj = null;
        for (Object obj2 : IrUtilsKt.getFunctions(irClassSymbol)) {
            if (Intrinsics.areEqual(((IrSimpleFunctionSymbol) obj2).getOwner().getName().asString(), str)) {
                if (z) {
                    w01.a("Sequence contains more than one matching element.");
                    return null;
                }
                z = true;
                obj = obj2;
            }
        }
        if (z) {
            return (IrSimpleFunctionSymbol) obj;
        }
        hb9.a("Sequence contains no element matching the predicate.");
        return null;
    }
}
