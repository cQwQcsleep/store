package org.jetbrains.kotlin.backend.common.lower.loops;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0016R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/lower/loops/WithIndexHeaderInfo;", "Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfo;", "nestedInfo", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfo;)V", "getNestedInfo", "()Lorg/jetbrains/kotlin/backend/common/lower/loops/HeaderInfo;", "asReversed", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class WithIndexHeaderInfo extends HeaderInfo {
    private final HeaderInfo nestedInfo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WithIndexHeaderInfo(HeaderInfo headerInfo) {
        super(null);
        headerInfo.getClass();
        this.nestedInfo = headerInfo;
    }

    @Override // org.jetbrains.kotlin.backend.common.lower.loops.HeaderInfo
    public HeaderInfo asReversed() {
        return null;
    }

    public final HeaderInfo getNestedInfo() {
        return this.nestedInfo;
    }
}
