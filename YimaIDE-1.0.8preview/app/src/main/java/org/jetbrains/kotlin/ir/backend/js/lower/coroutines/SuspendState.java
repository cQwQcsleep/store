package org.jetbrains.kotlin.ir.backend.js.lower.coroutines;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.backend.js.ir.JsIrBuilder;
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression;
import org.jetbrains.kotlin.ir.types.IrType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/lower/coroutines/SuspendState;", "", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "<init>", "(Lorg/jetbrains/kotlin/ir/types/IrType;)V", "entryBlock", "Lorg/jetbrains/kotlin/ir/expressions/IrContainerExpression;", "getEntryBlock", "()Lorg/jetbrains/kotlin/ir/expressions/IrContainerExpression;", "successors", "", "getSuccessors", "()Ljava/util/Set;", "id", "", "getId", "()I", "setId", "(I)V", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class SuspendState {
    private final IrContainerExpression entryBlock;
    private int id;
    private final Set<SuspendState> successors;

    public SuspendState(IrType irType) {
        irType.getClass();
        this.entryBlock = JsIrBuilder.buildComposite$default(JsIrBuilder.INSTANCE, irType, null, 2, null);
        this.successors = new LinkedHashSet();
        this.id = -1;
    }

    public final IrContainerExpression getEntryBlock() {
        return this.entryBlock;
    }

    public final int getId() {
        return this.id;
    }

    public final Set<SuspendState> getSuccessors() {
        return this.successors;
    }

    public final void setId(int i) {
        this.id = i;
    }
}
