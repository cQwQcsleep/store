package org.jetbrains.kotlin.backend.jvm.codegen;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/codegen/ExpressionInfo;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "blockInfo", "Lorg/jetbrains/kotlin/backend/jvm/codegen/BlockInfo;", "getBlockInfo", "()Lorg/jetbrains/kotlin/backend/jvm/codegen/BlockInfo;", "setBlockInfo", "(Lorg/jetbrains/kotlin/backend/jvm/codegen/BlockInfo;)V", "Lorg/jetbrains/kotlin/backend/jvm/codegen/LoopInfo;", "Lorg/jetbrains/kotlin/backend/jvm/codegen/TryInfo;", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ExpressionInfo {
    private BlockInfo blockInfo;

    public /* synthetic */ ExpressionInfo(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final BlockInfo getBlockInfo() {
        return this.blockInfo;
    }

    public final void setBlockInfo(BlockInfo blockInfo) {
        this.blockInfo = blockInfo;
    }

    private ExpressionInfo() {
    }
}
