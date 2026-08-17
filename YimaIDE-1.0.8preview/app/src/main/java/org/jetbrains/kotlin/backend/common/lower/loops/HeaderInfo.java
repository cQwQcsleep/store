package org.jetbrains.kotlin.backend.common.lower.loops;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0000H&\u0082\u0001\u0005\u0005\u0006\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfo;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "asReversed", "Lorg/jetbrains/kotlin/backend/common/lower/loops/ComparableRangeInfo;", "Lorg/jetbrains/kotlin/backend/common/lower/loops/FloatingPointRangeHeaderInfo;", "Lorg/jetbrains/kotlin/backend/common/lower/loops/IterableHeaderInfo;", "Lorg/jetbrains/kotlin/backend/common/lower/loops/NumericHeaderInfo;", "Lorg/jetbrains/kotlin/backend/common/lower/loops/WithIndexHeaderInfo;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class HeaderInfo {
    public /* synthetic */ HeaderInfo(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract HeaderInfo asReversed();

    private HeaderInfo() {
    }
}
