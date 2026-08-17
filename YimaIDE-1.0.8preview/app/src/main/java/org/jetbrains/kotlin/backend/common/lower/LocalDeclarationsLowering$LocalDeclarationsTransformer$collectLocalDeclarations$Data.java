package org.jetbrains.kotlin.backend.common.lower;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.IrFileEntry;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"org/jetbrains/kotlin/backend/common/lower/LocalDeclarationsLowering$LocalDeclarationsTransformer$collectLocalDeclarations$Data", "", "isInInlineFunction", "", "sourceFileWhenInlined", "Lorg/jetbrains/kotlin/ir/IrFileEntry;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(ZLorg/jetbrains/kotlin/ir/IrFileEntry;)V", "()Z", "getSourceFileWhenInlined", "()Lorg/jetbrains/kotlin/ir/IrFileEntry;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LocalDeclarationsLowering$LocalDeclarationsTransformer$collectLocalDeclarations$Data {
    private final boolean isInInlineFunction;
    private final IrFileEntry sourceFileWhenInlined;

    public /* synthetic */ LocalDeclarationsLowering$LocalDeclarationsTransformer$collectLocalDeclarations$Data(boolean z, IrFileEntry irFileEntry, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, (i & 2) != 0 ? null : irFileEntry);
    }

    public final IrFileEntry getSourceFileWhenInlined() {
        return this.sourceFileWhenInlined;
    }

    /* JADX INFO: renamed from: isInInlineFunction, reason: from getter */
    public final boolean getIsInInlineFunction() {
        return this.isInInlineFunction;
    }

    public LocalDeclarationsLowering$LocalDeclarationsTransformer$collectLocalDeclarations$Data(boolean z, IrFileEntry irFileEntry) {
        this.isInInlineFunction = z;
        this.sourceFileWhenInlined = irFileEntry;
    }
}
