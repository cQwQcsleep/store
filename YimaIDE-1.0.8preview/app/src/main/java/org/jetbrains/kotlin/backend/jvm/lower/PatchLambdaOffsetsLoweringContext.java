package org.jetbrains.kotlin.backend.jvm.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/backend/jvm/lower/PatchLambdaOffsetsLoweringContext;", "", "startOffset", "", "endOffset", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "getStartOffset", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEndOffset", "org.jetbrains.kotlin:backend.jvm.lower"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PatchLambdaOffsetsLoweringContext {
    private final Integer endOffset;
    private final Integer startOffset;

    public /* synthetic */ PatchLambdaOffsetsLoweringContext(Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2);
    }

    public final Integer getEndOffset() {
        return this.endOffset;
    }

    public final Integer getStartOffset() {
        return this.startOffset;
    }

    public PatchLambdaOffsetsLoweringContext(Integer num, Integer num2) {
        this.startOffset = num;
        this.endOffset = num2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PatchLambdaOffsetsLoweringContext() {
        Integer num = null;
        this(num, num, 3, num);
    }
}
