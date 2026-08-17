package org.jetbrains.kotlin.backend.common.actualizer;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"isStaticFun", "", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "org.jetbrains.kotlin:ir.actualization"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IrExpectActualMatchingContextKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isStaticFun(IrDeclaration irDeclaration) {
        return (irDeclaration instanceof IrSimpleFunction) && IrUtilsKt.isStatic((IrFunction) irDeclaration);
    }
}
