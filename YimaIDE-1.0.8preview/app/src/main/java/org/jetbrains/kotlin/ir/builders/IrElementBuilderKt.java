package org.jetbrains.kotlin.ir.builders;

import kotlin.Metadata;
import org.jetbrains.kotlin.ir.IrElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"setSourceRange", "", "Lorg/jetbrains/kotlin/ir/builders/IrElementBuilder;", "from", "Lorg/jetbrains/kotlin/ir/IrElement;", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IrElementBuilderKt {
    public static final void setSourceRange(IrElementBuilder irElementBuilder, IrElement irElement) {
        irElementBuilder.getClass();
        irElement.getClass();
        irElementBuilder.setStartOffset(irElement.getStartOffset());
        irElementBuilder.setEndOffset(irElement.getEndOffset());
    }
}
