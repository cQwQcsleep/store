package org.jetbrains.kotlin.backend.jvm.lower;

import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.backend.jvm.ir.JvmIrCoroutineUtilsKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.visitors.IrVisitorVoid;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"capturesCrossinline", "", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class SuspendLambdaLoweringKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean capturesCrossinline(IrFunction irFunction) {
        final Set set = SequencesKt.toSet(IrUtilsKt.getParents(irFunction));
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        irFunction.acceptChildren(new IrVisitorVoid() { // from class: org.jetbrains.kotlin.backend.jvm.lower.SuspendLambdaLoweringKt$capturesCrossinline$$inlined$hasChild$1
            public void visitElement(IrElement element) {
                element.getClass();
                if (booleanRef.element) {
                    return;
                }
                if ((element instanceof IrGetValue) && JvmIrCoroutineUtilsKt.isReadOfCrossinline((IrExpression) element) && set.contains(((IrGetValue) element).getSymbol().getOwner().getParent())) {
                    booleanRef.element = true;
                } else {
                    element.acceptChildren(this, (Object) null);
                }
            }
        }, (Object) null);
        return booleanRef.element;
    }
}
