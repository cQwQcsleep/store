package org.jetbrains.kotlin.backend.common.linkage.issues;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IdSignatureRenderer;
import org.jetbrains.kotlin.ir.util.IdSignatureRendererKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0019\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/linkage/issues/IrSymbolTypeMismatchException;", "Lorg/jetbrains/kotlin/backend/common/linkage/issues/IrDeserializationException;", "expected", "Ljava/lang/Class;", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "actual", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/Class;Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;)V", "getExpected", "()Ljava/lang/Class;", "getActual", "()Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IrSymbolTypeMismatchException extends IrDeserializationException {
    private final IrSymbol actual;
    private final Class<? extends IrSymbol> expected;

    public IrSymbolTypeMismatchException(Class<? extends IrSymbol> cls, IrSymbol irSymbol) {
        String strRender$default;
        cls.getClass();
        irSymbol.getClass();
        StringBuilder sb = new StringBuilder("The symbol of unexpected type encountered during IR deserialization: ");
        sb.append(irSymbol.getClass().getSimpleName());
        sb.append(", ");
        IdSignature signature = irSymbol.getSignature();
        sb.append((signature == null || (strRender$default = IdSignatureRendererKt.render$default(signature, (IdSignatureRenderer) null, 1, (Object) null)) == null) ? irSymbol.toString() : strRender$default);
        sb.append(". ");
        sb.append(cls.getSimpleName());
        sb.append(" is expected.");
        super(sb.toString(), null);
        this.expected = cls;
        this.actual = irSymbol;
    }

    public final IrSymbol getActual() {
        return this.actual;
    }

    public final Class<? extends IrSymbol> getExpected() {
        return this.expected;
    }
}
